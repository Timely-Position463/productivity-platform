package com.ajay.productivity.utility.imageToPdf.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageToPdfService {
    Resource convertImageToPdf(List<MultipartFile> images);
}
