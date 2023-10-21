package com.spring.health.repository;



import com.spring.health.model.CurrentSession;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends MongoRepository<CurrentSession, ObjectId> {
	public CurrentSession findByUuid(String uuid);
}
