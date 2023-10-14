package com.spring.health.repository;


import com.spring.health.Dto.LoginDto;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.health.model.Doctor;

@Repository
public interface DoctorRepository extends MongoRepository<Doctor, ObjectId> {
    boolean existsByEmailAndIdNotIn(String email, ObjectId id);
    LoginDto findByEmail(String email);
}
