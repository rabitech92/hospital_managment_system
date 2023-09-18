package com.spring.health.service;

import com.spring.health.Dto.FileInfoDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    FileInfoDto uploadFile(MultipartFile file) throws IOException;
    FileInfoDto downloadFile(String id);

}
