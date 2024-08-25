package com.upload.download.controller;

import com.upload.download.sevice.FileService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

@RestController
@RequiredArgsConstructor
public class FileController {

    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file){
        try {
            boolean uploadFile = fileService.fileUpload(file);
            if (uploadFile){
                return new ResponseEntity<>("upload success", HttpStatus.CREATED);
            }else {
                return new ResponseEntity<>("upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/download")
    public ResponseEntity<?> downloadFile(@RequestParam String file){
        try {
            byte[] downloadFile = fileService.downloadFile(file);
            String contentType = getContentType(file);

            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.parseMediaType(contentType));
            header.setContentDispositionFormData("Attachment",file);
            return ResponseEntity.ok().headers(header).body(downloadFile);

        }catch (FileNotFoundException e) {
            return new ResponseEntity<>("file not found", HttpStatus.NOT_FOUND);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    public String getContentType(String fileName) {
        String extension = FilenameUtils.getExtension(fileName);

        switch (extension) {
            case "pdf":
                return "application/pdf";
            case "xlsx":
                return "application/vnd.openxmlformats-officedocument.spreadsheettml.sheet";
            case "txt":
                return "text/plan";
            case "png":
                return "image/png";
            case "jpeg":
                return "image/jpeg";
            default:
                return "application/octet-stream";
        }

    }
}
