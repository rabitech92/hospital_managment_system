package com.spring.health.repository;

import com.spring.health.model.CurrentSession;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SessionRepository extends MongoRepository <CurrentSession, ObjectId>{
    CurrentSession findByUuid(String uuid);
}
