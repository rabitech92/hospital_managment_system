package com.spring.health.service.impl;

import com.spring.health.Dto.Response;
import com.spring.health.mapper.FileStorageMapper;
import com.spring.health.model.ImageData;
import com.spring.health.repository.FileStorageRepository;
import com.spring.health.service.FileStorageService;
import com.spring.health.util.ImageUtils;
import com.spring.health.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileStorageServiceImpl implements FileStorageService {

    private final FileStorageRepository fileStorageRepository;
    private final FileStorageMapper fileStorageMapper;



    @Override
    public Response uploadImage(MultipartFile file) throws IOException {
        ImageData imageData=fileStorageRepository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (imageData!=null){
            return ResponseBuilder.getSuccessResponse(HttpStatus.ACCEPTED,"file uploaded successfully : ",file.getOriginalFilename());
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Internal Server Error");
    }

    @Override
    public Response downloadImage(String fileName) {
        ImageData imageData=fileStorageRepository.findByName(fileName);
//        byte[] images=ImageUtils.decompressImage()
                //ImageUtils.decompressImage(dbImageData.get().getImageData());
        return null;
    }
}
