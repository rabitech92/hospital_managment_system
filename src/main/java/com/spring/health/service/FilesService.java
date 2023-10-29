package com.spring.health.service;

import com.spring.health.Dto.FileInfoDto;
import org.bson.types.ObjectId;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FilesService {
    FileInfoDto uploadFile(MultipartFile file) throws IOException;


}
