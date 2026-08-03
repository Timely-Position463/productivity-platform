package com.ajay.productivity.utility;

import com.ajay.productivity.utility.exception.InvalidPdfException;
import com.ajay.productivity.utility.pdfToImage.validation.PdfValidationServiceImpl;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@ExtendWith(MockitoExtension.class)
public class PDFValidationServiceImplTest {

    private PdfValidationServiceImpl service;


    @BeforeEach
    void setup() {
        service = new PdfValidationServiceImpl();
    }

    @Test
    @DisplayName("Should throw exception when file is Null")
    public void shouldThrowExceptionWhenFileIsNull() {
        assertThrows(InvalidPdfException.class, () -> service.validatePdf(null));
    }

    @Test
    @DisplayName("Should throw exception when file is Empty")
    public void shouldThrowExceptionWhenFileIsEmpty() {
        MockMultipartFile file = new MockMultipartFile("file", "".getBytes());
        assertThrows(InvalidPdfException.class, () -> service.validatePdf(file));
    }

    @Test
    @DisplayName("Should throw exception when file is not a PDF")
    public void shouldThrowExceptionWhenFileTypeIsNotPdf() {
        MockMultipartFile file = new MockMultipartFile("file", "image.png", "application/png", "hello".getBytes());
        assertThrows(InvalidPdfException.class, () -> service.validatePdf(file));
    }

    @Test
    @DisplayName("Should throw exception when file is corrupted")
    public void shouldThrowExceptionWhenFileIsCorrupted() {
        MockMultipartFile file = new MockMultipartFile("file", "file.png", "application/pdf", "hello".getBytes());
        assertThrows(InvalidPdfException.class, () -> service.validatePdf(file));
    }
    @Test
    @DisplayName("Should not throw exception when file is a valid PDF")
    public void shouldNotThrowExceptionWhenPdfIsValid() {
        try(PDDocument document=new PDDocument()) {
            document.addPage(new PDPage());
            ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
            document.save(outputStream);
            byte[] pdfBytes = outputStream.toByteArray();
            MockMultipartFile file=new MockMultipartFile("file","fileName.pdf","application/pdf", pdfBytes);
            assertDoesNotThrow(() -> service.validatePdf(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
