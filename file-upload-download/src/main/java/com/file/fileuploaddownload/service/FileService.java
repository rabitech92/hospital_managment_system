package com.file.fileuploaddownload.service;

import com.file.fileuploaddownload.model.FileInfo;
import com.file.fileuploaddownload.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    private final String pathRoot = System.getProperty("user.home");

    public FileInfo uploadFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFilename(file.getOriginalFilename());
        fileInfo.setSize(file.getSize());
        fileInfo.setContentType(file.getContentType());
        Path pathFile = Paths.get(pathRoot+ File.separator+fileName+"rabiul"+file.getOriginalFilename());
        String s = pathFile.toString();
        fileInfo.setFilePath(Base64.getEncoder().encodeToString((s.getBytes())));
        Files.write(pathFile,file.getBytes());
        return fileRepository.save(fileInfo);

    }
}
