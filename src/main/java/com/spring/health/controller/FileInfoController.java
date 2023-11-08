package com.spring.health.controller;


import com.spring.health.Dto.FileInfoDto;
import com.spring.health.model.BaseClass;
import com.spring.health.model.Doctor;
import com.spring.health.model.FileInfo;
import com.spring.health.service.FilesService;
import org.bson.types.ObjectId;
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

    @PostMapping("/file-save")
    public FileInfoDto fileSave(@RequestParam(value = "docName",required = false) String docName, @RequestParam(value = "file",required = false ) MultipartFile file,
                                @RequestParam(value = "className",required = false) String className, @RequestParam(value = "id",required = false) String id){
        try {
            Class<? extends BaseClass> baseClass = (Class<? extends BaseClass>) Class.forName(className);
            ObjectId objectId = new ObjectId(id);
            return fileService.saveFile(docName, file, baseClass, objectId);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


}
