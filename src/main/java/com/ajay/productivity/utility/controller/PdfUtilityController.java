package com.ajay.productivity.utility.controller;

import com.ajay.productivity.document.temporary.TemporaryFileService;
import com.ajay.productivity.utility.imageToPdf.service.ImageToPdfService;
import com.ajay.productivity.utility.pdfToImage.service.PdfToImageService;
import com.ajay.productivity.utility.pdfToImage.validation.PdfValidationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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


@Tag(
        name = "Document Utilities",
        description = "Utilities for document conversion and processing."
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/utilities")
public class PdfUtilityController {
    private final ImageToPdfService imageToPdfService;
    private final PdfToImageService pdfToImageService;
    private final TemporaryFileService temporaryFileService;
    private final PdfValidationService pdfValidationService;

    @Operation(
            summary = "Convert Images to PDF",
            description = "Combines uploaded images into a single PDF document."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "PDF generated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid image files"),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    @PostMapping(value = "/image-to-pdf", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Resource> convertImageToPdf(@RequestParam List<MultipartFile> images) {
        Resource pdf = imageToPdfService.convertImageToPdf(images);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"image-to-pdf.pdf\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    @Operation(
            summary = "Convert PDF to Images",
            description = "Converts every page of an uploaded PDF into PNG images and returns them as a ZIP archive."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ZIP archive generated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid or corrupted PDF"),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
    })
    @PostMapping(value = "/pdf-to-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<StreamingResponseBody> convertPdfToImage(
            @Parameter(
                    description = "PDF file to convert",
                    required = true
            )
            @RequestParam MultipartFile file) {
        pdfValidationService.validatePdf(file);
        StreamingResponseBody body = outputStream -> {
            Path tempFilePath = temporaryFileService.createTemporaryFile(file);
            try {
                pdfToImageService.convertPdfToImage(tempFilePath, outputStream);
            } finally {
                temporaryFileService.delete(tempFilePath);
            }
        };
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"converted-images.zip\"")
                .contentType(MediaType.parseMediaType("application/zip"))
                .body(body);
    }
}
