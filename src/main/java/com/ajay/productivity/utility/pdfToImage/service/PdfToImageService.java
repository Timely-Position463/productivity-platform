package com.ajay.productivity.utility.pdfToImage.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;

public interface PdfToImageService {
    public void convertPdfToImage(Path filePath, OutputStream outputStream) throws IOException;
}