package com.spring.health.controller;

import com.spring.health.model.FileInfo;
import com.spring.health.service.FilesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/files")
public class FileInfoController {

    private final FilesService fileService;

    public FileInfoController(FilesService fileService) {
        this.fileService = fileService;
    }
    @PostMapping
    public ResponseEntity<?> upLoad(@RequestParam("file") MultipartFile file ) throws IOException{
        try {
            fileService.uploadFile(file);
            return ResponseEntity.status(HttpStatus.OK).body("File Upload Successful");
            }catch (IOException e){
            return ResponseEntity.status(HttpStatus.OK).body("File Upload Failed");
        }

    }
}
