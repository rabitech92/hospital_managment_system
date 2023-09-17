package com.spring.health.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.health.model.Doctor;

@Repository
public interface DoctorRepository extends MongoRepository<Doctor, ObjectId> {
    boolean existsByEmailAndIdNotIn(String email, ObjectId id);
    Doctor findByEmail(String email);

}
