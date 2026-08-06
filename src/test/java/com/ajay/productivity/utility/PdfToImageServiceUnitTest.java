package com.ajay.productivity.utility;

import com.ajay.productivity.utility.pdfToImage.service.PdfToImageServiceImpl;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class PdfToImageServiceUnitTest {
    private PdfToImageServiceImpl service;
    @BeforeEach
    void setup(){
        service=new PdfToImageServiceImpl();
    }

    @Test
    @DisplayName("Should return a zip file with images of the pdf")
    public void shouldReturnZipFromPdf(){
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        service.convertPdfToImage(Path.of("src/test/resources/pdf/file.pdf"),outputStream);
        assertTrue(outputStream.size()>0);
        byte[] bytes = outputStream.toByteArray();
        assertEquals('P', bytes[0]);
        assertEquals('K', bytes[1]);
    }

}
