package com.ajay.productivity.document.temporary;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface TemporaryFileService {

    Path createTemporaryFile(MultipartFile file);

    void delete(Path path);

}