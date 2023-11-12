package com.spring.health.controller;


import com.spring.health.Dto.FileInfoDto;
import com.spring.health.model.BaseClass;
import com.spring.health.model.Doctor;
import com.spring.health.model.FileInfo;
import com.spring.health.service.FilesService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
//import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Optional;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileInfoController {

    private final FilesService fileService;
    private final ModelMapper modelMapper;


    @PostMapping("/save")
    public FileInfoDto upLoad(@RequestParam("file") MultipartFile file ) throws IOException{
            return fileService.uploadFile(file);
    }

//    @GetMapping("/{id}")
//    public Resource downloadFile(@PathVariable("id") ObjectId id) throws IOException {
//        return fileService.downloadFile(id);
//    }
    @GetMapping("/{id}")
    public byte[] downloadFile(HttpServletResponse response, @PathVariable("id") ObjectId id) throws IOException {
        Optional<FileInfo> fileInfo=fileService.finByID(id);

        String decodedFilePath=new String(Base64.getDecoder().decode(fileInfo.get().getFilePath().getBytes()));

        File downloadableFile=new File(decodedFilePath);

        InputStream in=new FileInputStream(downloadableFile);
        response.setHeader("Content-Disposition",
                "attachment; filename=" + fileInfo.get().getFilename().replaceAll("[^a-zA-Z0-9.]", ""));
        return IOUtils.toByteArray(in);
//        return fileService.downloadFile(id);
    }

}
