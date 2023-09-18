package com.spring.health.controller;

import com.spring.health.model.FileInfo;
import com.spring.health.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/files")
public class FileInfoController {

    private final FileService fileService;

    public FileInfoController(FileService fileService) {
        this.fileService = fileService;
    }
    @PostMapping
    public ResponseEntity<String> upLoad(@RequestParam("file") MultipartFile file ) throws IOException{
        try {
            fileService.uploadFile(file);
            return ResponseEntity.status(HttpStatus.OK).body("File Upload Successful");
            }catch (IOException e){
            System.out.println(" exception "+ e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File Upload Failed");
        }

    }
}
