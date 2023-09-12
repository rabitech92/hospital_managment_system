package com.spring.health.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.health.model.Patient;



@Repository
public interface PatientRepository  extends MongoRepository<Patient, ObjectId> {

}
