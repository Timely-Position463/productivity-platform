package com.ajay.productivity.utility.pdfToImage.validation;

import com.ajay.productivity.document.temporary.TemporaryFileService;
import com.ajay.productivity.utility.exception.InvalidPdfException;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PdfValidationServiceImpl implements PdfValidationService{
    private final TemporaryFileService temporaryFileService;

    @Override
    public void validatePdf(MultipartFile file) {
        if(file==null || file.isEmpty()){
            throw new InvalidPdfException("The provided PDF is missing or invalid");
        }
        if(!Objects.equals(file.getContentType(),"application/pdf")){
            throw new InvalidPdfException("No PDF file was provided");
        }
        Path filePath=temporaryFileService.createTemporaryFile(file);
        try(PDDocument document= Loader.loadPDF(filePath.toFile())){

        }
        catch (IOException e){
            throw new InvalidPdfException("The provided file is corrupted",e);
        }
        finally {
            temporaryFileService.delete(filePath);
        }

    }
}
