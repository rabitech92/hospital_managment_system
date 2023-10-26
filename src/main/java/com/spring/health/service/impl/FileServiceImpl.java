package com.spring.health.service.impl;

import com.spring.health.Dto.FileInfoDto;
import com.spring.health.model.FileInfo;
import com.spring.health.repository.FileInfoRepository;
import com.spring.health.service.FilesService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;


@Service
public class FileServiceImpl implements FilesService {


    private final FileInfoRepository fileInfoRepository;
    private final ModelMapper modelMapper;

    public FileServiceImpl(FileInfoRepository fileInfoRepository, ModelMapper modelMapper) {
        this.fileInfoRepository = fileInfoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public FileInfoDto uploadFile(MultipartFile file) throws IOException {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFilename(file.getOriginalFilename());
        fileInfo.setSize(file.getSize());
        fileInfo.setContentType(file.getContentType());
        fileInfo.setUploadDate(new Date());
//        Path filePath = Paths.get("Directory",file.getOriginalFilename());
//        Files.write(filePath, file.getBytes());
        return convertrDto(fileInfoRepository.save(fileInfo));
    }

    @Override
    public FileInfoDto downloadFile(String id) {
        return null;
    }

    private FileInfoDto convertrDto(FileInfo fileInfo) {
        FileInfoDto fileInfoDto = modelMapper.map(fileInfo, FileInfoDto.class);
        return fileInfoDto;

    }


}
