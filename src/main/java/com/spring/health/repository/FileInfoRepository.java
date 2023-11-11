package com.spring.health.repository;

import com.spring.health.model.FileInfo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileInfoRepository extends MongoRepository<FileInfo, ObjectId> {
    int countByFileLocation(String fileLocation);
    Optional<FileInfo> findByFilePath(String filePath);
    Optional<FileInfo> findByEntityAndEntityRowIdAndActiveStatus(String className,ObjectId rowId, Integer id);



}
