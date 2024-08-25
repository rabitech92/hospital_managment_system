package com.upload.download.sevice;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    public boolean fileUpload(MultipartFile file)throws Exception;
    public byte[] downloadFile(String file)throws Exception;
}
