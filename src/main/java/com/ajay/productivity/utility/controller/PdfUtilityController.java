package com.ajay.productivity.utility.controller;

import com.ajay.productivity.document.temporary.TemporaryFileService;
import com.ajay.productivity.utility.imageToPdf.service.ImageToPdfService;
import com.ajay.productivity.utility.pdfToImage.service.PdfToImageService;
import com.ajay.productivity.utility.pdfToImage.validation.PdfValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/utilities")
public class PdfUtilityController {
    private final ImageToPdfService imageToPdfService;
    private final PdfToImageService pdfToImageService;
    private final TemporaryFileService temporaryFileService;
    private final PdfValidationService pdfValidationService;

    @PostMapping("/image-to-pdf")
    public ResponseEntity<Resource> convertImageToPdf(@RequestParam List<MultipartFile> images){
        Resource pdf= imageToPdfService.convertImageToPdf(images);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"image-to-pdf.pdf\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    @PostMapping("/pdf-to-image")
    public ResponseEntity<StreamingResponseBody> convertPdfToImage(@RequestParam MultipartFile file) {
        pdfValidationService.validatePdf(file);
            StreamingResponseBody body= outputStream-> {
                Path tempFilePath = temporaryFileService.createTemporaryFile(file);
                try {
                    pdfToImageService.convertPdfToImage(tempFilePath, outputStream);
                }finally {
                    temporaryFileService.delete(tempFilePath);
                }
            };
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"converted-images.zip\"")
                .contentType(MediaType.parseMediaType("application/zip"))
                .body(body);
    }
}
