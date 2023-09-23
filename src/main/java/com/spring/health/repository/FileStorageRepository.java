package com.spring.health.repository;

import com.spring.health.Dto.Response;
import com.spring.health.model.ImageData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileStorageRepository extends MongoRepository<ImageData,Long> {
    ImageData findByName(String fileName);
}
