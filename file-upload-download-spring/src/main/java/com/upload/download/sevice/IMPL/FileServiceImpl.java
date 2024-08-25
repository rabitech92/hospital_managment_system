package com.upload.download.sevice.IMPL;

import com.upload.download.sevice.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {

    @Value("${file.upload.path}")
    public String uploadPath;

    @Override
    public boolean fileUpload(MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        File  saveFile =new File(uploadPath);

        if (!saveFile.exists()){
            saveFile.mkdir();
        }
        String storePath = uploadPath.concat(fileName);
        long upload = Files.copy(file.getInputStream(), Paths.get(storePath));
        if (upload!=0){
            return true;
        }

        return false;
    }

    @Override
    public byte[] downloadFile(String file) throws Exception {
        String fullPath = uploadPath.concat(file); // file/spring_rest.pptx
        try {
            InputStream ios = new FileInputStream(fullPath);
            return StreamUtils.copyToByteArray(ios);
        }catch (FileNotFoundException e){
            e.printStackTrace();
            throw e;
        }catch (IIOException e){
            e.printStackTrace();
            throw e;
        }
    }
}
