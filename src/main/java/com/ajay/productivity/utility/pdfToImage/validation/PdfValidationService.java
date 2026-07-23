package com.ajay.productivity.utility.pdfToImage.validation;

import org.springframework.web.multipart.MultipartFile;

public interface PdfValidationService {
    void validatePdf(MultipartFile file);
}
