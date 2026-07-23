package com.ajay.productivity.document.temporary;

import com.ajay.productivity.document.temporary.exception.TemporaryFileException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@Service
public class TemporaryFileServiceImpl implements TemporaryFileService {
    @Override
    public Path createTemporaryFile(MultipartFile file){
        try {
            Path tempFile = Files.createTempFile("document-",".pdf");
            file.transferTo(tempFile.toFile());
            return tempFile;
        }catch (IOException e){
            throw new TemporaryFileException("Failed to create Temporary file",e);
        }
    }

    @Override
    public void delete(Path path) {
        try{
            Files.deleteIfExists(path);
        }catch (IOException e){
            log.warn("Failed to delete Temporary file: {}",path,e);
        }
    }
}
