package com.ajay.productivity.utility.controller;

import com.ajay.productivity.utility.imageToPdf.service.ImageToPdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/utilities")
public class UtilityController {
    private final ImageToPdfService imageToPdfService;

    @PostMapping("/image-to-pdf")
    public ResponseEntity<Resource> convertImageToPdf(@RequestParam List<MultipartFile> images){
        Resource pdf= imageToPdfService.convertImageToPdf(images);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"image-to-pdf.pdf\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
