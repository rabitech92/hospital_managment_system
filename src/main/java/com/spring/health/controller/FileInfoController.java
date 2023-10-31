package com.spring.health.controller;


import com.spring.health.Dto.FileInfoDto;
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
    public FileInfoDto upLoad(@RequestParam("file") MultipartFile file ) throws IOException{
            return fileService.uploadFile(file);
    }

    @GetMapping("/{download}")
    public FileInfoDto downloadFile(@PathVariable("download") String filePath) throws IOException {
        return fileService.downloadFile(filePath);
    }
}
