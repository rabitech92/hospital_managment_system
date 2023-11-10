package com.spring.health.service.impl;

import com.spring.health.Dto.FileInfoDto;
import com.spring.health.enums.Status;
import com.spring.health.model.BaseClass;
import com.spring.health.model.FileInfo;
import com.spring.health.repository.FileInfoRepository;
import com.spring.health.service.FilesService;
import com.spring.health.util.DateUtils;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FilesService {


    private final FileInfoRepository fileInfoRepository;
    private final ModelMapper modelMapper;
    private final String fileRootLocation = System.getProperty("user.home");


    private final String PATH_FOLDER = "E:\\Rabiul-islam\\File-Spring-App\\";


    @Override
    public FileInfoDto uploadFile(MultipartFile file) throws IOException {
        String fileRoot = fileRootLocation +file.getOriginalFilename();
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFilename(file.getOriginalFilename());
        fileInfo.setSize(file.getSize());
        fileInfo.setContentType(file.getContentType());
        fileInfo.setUploadDate(new Date());
        Path filePath = Paths.get(fileRootLocation,file.getOriginalFilename());
        fileInfo.setFilePath(fileRoot);
        Files.write(filePath, file.getBytes());
        return convertrDto(fileInfoRepository.save(fileInfo));
    }

    @Override
    public FileInfoDto downloadFile(String filePath) throws IOException {
        Optional<FileInfo> fileInfoDto = fileInfoRepository.findByFilePath(filePath);
        String fileLocation = fileInfoDto.get().getFilePath();
        return convertByte(Files.readAllBytes(new File(fileLocation).toPath()));
    }

    @Override
    public FileInfoDto saveFile(String docName, MultipartFile file, Class<? extends BaseClass> modelClass, ObjectId rowId) {
        if (file.isEmpty()) {
            return null;
        }

        Optional<FileInfo> result = fileInfoRepository.findByEntityAndEntityRowIdAndActiveStatus(
                Base64.getEncoder().encodeToString(modelClass.getName().getBytes()), rowId,
                Status.ACTIVE.getValue());
        String entityName = modelClass.getName();
        String encodedEntityName = Base64.getEncoder().encodeToString(entityName.getBytes());
        String fileName = file.getOriginalFilename();
        String location = getUniqueLocation(encodedEntityName, fileName);
        if (fileUpload(file, location)) {
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFileType(docName);
            fileInfo.setFilename(fileName);
            fileInfo.setEntity(encodedEntityName);
            fileInfo.setFileLocation(Base64.getEncoder().encodeToString(location.getBytes()));
            fileInfo.setEntityRowId(rowId);
            fileInfo.setActiveStatus(Status.ACTIVE.getValue());
            result.ifPresent(info -> fileInfo.setId(info.getId()));
            return convertrDto(fileInfoRepository.save(fileInfo));
        }
        return null;
    }

    private String getUniqueLocation(String entityName, String filename) {
        String location = fileRootLocation + File.separator + "Support Util Files " + File.separator + entityName
                + File.separator + DateUtils.getStringDate(new Date(), "yy-mm-dd") + File.separator
                + filename;
        int existingLocationCount = fileInfoRepository.countByFileLocation(location);
        if (existingLocationCount == 0) {
            return location;
        }
        return getUniqueLocation(entityName, filename);
    }


    public Boolean fileUpload(MultipartFile multipartFile, String location) {
        try {
            String directionLocation = location.substring(0, location.lastIndexOf(File.separator));
            Path folderPath = Paths.get(directionLocation);
            if (!Files.exists(folderPath)) {
                Files.createDirectories(folderPath);
            }
            Path path = Paths.get(
                    directionLocation + File.separator + multipartFile.getOriginalFilename());
            Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public FileInfo findFile(String classname, ObjectId id) {
        Optional<FileInfo> result = fileInfoRepository.findByEntityAndEntityRowIdAndActiveStatus(
                classname, id, Status.ACTIVE.getValue());
        return result.orElse(null);
    }

    @Override
    public void deleteImageIfExists(String name, ObjectId id) {
        FileInfo fileInfo = findFile(name, id);
        if (fileInfo != null) {
            fileInfoRepository.delete(fileInfo);
        }
    }


    private FileInfoDto convertrDto(FileInfo fileInfo) {
        FileInfoDto fileInfoDto = modelMapper.map(fileInfo, FileInfoDto.class);
        return fileInfoDto;

    }

    private FileInfoDto convertByte(byte[] fileInfo) {
        return modelMapper.map(fileInfo, FileInfoDto.class);
    }

}
