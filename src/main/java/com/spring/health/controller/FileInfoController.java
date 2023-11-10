package com.spring.health.controller;


import com.spring.health.Dto.FileInfoDto;
import com.spring.health.model.BaseClass;
import com.spring.health.model.Doctor;
import com.spring.health.model.FileInfo;
import com.spring.health.service.FilesService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileInfoController {

    private final FilesService fileService;

    @PostMapping("/save")
    public FileInfoDto upLoad(@RequestParam("file") MultipartFile file ) throws IOException{
            return fileService.uploadFile(file);
    }

    @GetMapping("/{download}")
    public FileInfoDto downloadFile(@PathVariable("download") String filePath) throws IOException {
        return fileService.downloadFile(filePath);
    }

}
