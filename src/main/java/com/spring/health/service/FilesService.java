package com.spring.health.service;

import com.spring.health.Dto.FileInfoDto;
import com.spring.health.model.BaseClass;
import org.bson.types.ObjectId;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FilesService {
    FileInfoDto uploadFile(MultipartFile file) throws IOException;
    FileInfoDto downloadFile(String fileName)throws IOException;
    public FileInfoDto saveFile(String docName, MultipartFile file,
                                Class<? extends BaseClass> modelClass, ObjectId rowId);
}
