package com.file.fileuploaddownload.controller;

import com.file.fileuploaddownload.model.FileInfo;
import com.file.fileuploaddownload.service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/saveInfo")
    public FileInfo upload(@RequestParam("file") MultipartFile file) throws IOException {
        return fileService.uploadFile(file);
    }

}
