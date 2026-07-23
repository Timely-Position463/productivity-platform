package com.ajay.productivity.utility.pdfToImage.service;

import com.ajay.productivity.utility.pdfToImage.exception.PdfToImageException;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class PdfToImageServiceImpl implements PdfToImageService {
    public static final int DEFAULT_DPI=300;
    public void convertPdfToImage(Path filePath, OutputStream outputStream) {
        try(PDDocument document= Loader.loadPDF(filePath.toFile());
            ZipOutputStream zipOutputStream=new ZipOutputStream(outputStream)
        ){
            PDFRenderer renderer=new PDFRenderer(document);
            for (int page=0;page<document.getNumberOfPages();page++){
                BufferedImage bufferedImage= renderer.renderImageWithDPI(page,DEFAULT_DPI);
                ZipEntry zipEntry=new ZipEntry("page"+(page +1)+".png");
                zipOutputStream.putNextEntry(zipEntry);
                ImageIO.write(bufferedImage,"png",zipOutputStream);
                zipOutputStream.closeEntry();
            }
        } catch (IOException e) {
            throw new PdfToImageException("Failed to convert PDF to Image", e);
        }
    }
}
