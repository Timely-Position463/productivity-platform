package com.ajay.productivity.integration;

import com.ajay.productivity.document.temporary.TemporaryFileServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class TemporaryFileServiceIntegrationTest {
    private TemporaryFileServiceImpl service;

    @BeforeEach
    void setup() {
        service = new TemporaryFileServiceImpl();
    }

    @Test
    @DisplayName("Should create temporary file")
    public void shouldCreateTemporaryFile() throws IOException {
        MockMultipartFile file = new MockMultipartFile("file", "report.pdf", "application/pdf", "Hello PDF".getBytes());
        byte[] fileBytes = file.getBytes();
        Path path = service.createTemporaryFile(file);
        try {
            assertNotNull(path);
            assertTrue(Files.exists(path));
            byte[] createdFileBytes = Files.readAllBytes(path);
            assertArrayEquals(fileBytes, createdFileBytes);
            assertTrue(path.getFileName().toString().endsWith(".pdf"));
        } finally {
            service.delete(path);
        }
    }

    @Test
    @DisplayName("Should delete temporary file")
    public void shouldDeleteTemporaryFile() {
        MockMultipartFile file = new MockMultipartFile("file", "report.pdf", "application/pdf", "HELLO".getBytes());
        Path path = service.createTemporaryFile(file);
        try {
            service.delete(path);
            assertFalse(Files.exists(path));
        } finally {
            service.delete(path);
        }
    }

    @Test
    @DisplayName("should not throw when deleting file twice")
    public void ShouldNotThrowWhenDeletingFileTwice() {
        MockMultipartFile file = new MockMultipartFile("file", "report.pdf", "application/pdf", "HELLO".getBytes());
        Path path = service.createTemporaryFile(file);
        assertDoesNotThrow(() -> {
            service.delete(path);
            service.delete(path);
        });
        assertFalse(Files.exists(path));
    }

}
