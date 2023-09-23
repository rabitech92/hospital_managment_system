package com.spring.health.service;

import com.spring.health.Dto.Response;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageService {

    public Response uploadImage(MultipartFile file) throws IOException;
    public Response downloadImage(String fileName);
}
