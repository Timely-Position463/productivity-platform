package com.ajay.productivity.util;

import org.springframework.mock.web.MockMultipartFile;

public class TestUtils {
    public MockMultipartFile createPdfFile(String content){
        return new MockMultipartFile("file","report.pdf","application/pdf",content.getBytes());
    }
    public MockMultipartFile createPngFile(String content){
        return new MockMultipartFile("file","report.png","application/png",content.getBytes());
    }

}
