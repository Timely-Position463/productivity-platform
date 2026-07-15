package com.ajay.productivity.utility.imageToPdf.service;

import com.ajay.productivity.utility.imageToPdf.exception.ImageValidationException;
import com.ajay.productivity.utility.imageToPdf.exception.PdfGenerationException;
import com.ajay.productivity.utility.util.ImagePlacement;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ImageToPdfServiceImpl implements ImageToPdfService {

    private static final int MAX_IMAGES = 10;
    private static final long MAX_FILE_SIZE = 10L * 1024 * 1024;
    private static final PDRectangle PAGE_SIZE = PDRectangle.A4;
    private static final Set<String> SUPPORTED_IMAGE_TYPES = Set.of(
            "image/png", "image/jpeg"
    );

    @Override
    public Resource convertImageToPdf(List<MultipartFile> images) {
        validateImages(images);
        List<BufferedImage> bufferedImages=new ArrayList<>();
        for (MultipartFile file : images) {
            BufferedImage bufferedImage = decodeImage(file);
            bufferedImages.add(bufferedImage);
        }
        return generatePdf(bufferedImages);
    }

    private BufferedImage decodeImage(MultipartFile file) {
        try {
            try (InputStream input = file.getInputStream()) {
                BufferedImage bufferedImage = ImageIO.read(input);
                if (bufferedImage == null)
                    throw new ImageValidationException("Unsupported Image: " + file.getOriginalFilename());
                return bufferedImage;
            }
        } catch (IOException e) {
            throw new ImageValidationException("Unsupported Image: " + file.getOriginalFilename(), e);
        }
    }

    private Resource generatePdf(List<BufferedImage> decodedImages) {
        try(PDDocument document = new PDDocument()){
            for(var decodedImage:decodedImages) {
                PDImageXObject pdfImage = LosslessFactory.createFromImage(document, decodedImage);
                PDPage page = new PDPage(PAGE_SIZE);
                document.addPage(page);
                try (var contentStream = new PDPageContentStream(document, page)) {
                    float pageWidth = page.getMediaBox().getWidth();
                    float pageHeight = page.getMediaBox().getHeight();

                    float imageWidth = decodedImage.getWidth();
                    float imageHeight = decodedImage.getHeight();
                    ImagePlacement placement = calculatePlacement(pageWidth, pageHeight, imageWidth, imageHeight);

                    contentStream.drawImage(pdfImage,
                            placement.x(),
                            placement.y(),
                            placement.width(),
                            placement.height()
                    );
                }
            }
            try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
                document.save(output);
                return new ByteArrayResource(output.toByteArray());

            }
        } catch (IOException e) {
            throw new PdfGenerationException("Failed to generate PDF", e);
        }
    }

    private void validateImages (List < MultipartFile > images) {
        if (images == null || images.isEmpty()) throw new ImageValidationException("Please input an image");
        if (images.size() > MAX_IMAGES)
            throw new ImageValidationException("Too many files detected. Maximum of "+MAX_IMAGES+" images are accepted.");
        for (MultipartFile file : images) {
            if (file.isEmpty())
                throw new ImageValidationException("This image is empty : " + file.getOriginalFilename());
            if (file.getSize() > MAX_FILE_SIZE)
                throw new ImageValidationException("Image " + file.getOriginalFilename() + " exceeds the maximum allowed size of "+MAX_FILE_SIZE);
            String contentType = file.getContentType();
            if (contentType == null || !SUPPORTED_IMAGE_TYPES.contains(contentType)) {
                throw new ImageValidationException("Only PNG and JPEG format images are supported.");
            }
        }
    }

    private ImagePlacement calculatePlacement(float pageWidth, float pageHeight, float imageWidth, float imageHeight) {
        float scaleWidth = pageWidth / imageWidth;
        float scaleHeight = pageHeight / imageHeight;

        float scale = Math.min(scaleWidth, scaleHeight);

        float newWidth = imageWidth * scale;
        float newHeight = imageHeight * scale;

        float x = (pageWidth - newWidth) / 2;
        float y = (pageHeight - newHeight) / 2;

        return new ImagePlacement(x, y, newWidth, newHeight);
    }
}