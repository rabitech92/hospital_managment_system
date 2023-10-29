package com.spring.health.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.health.model.Patient;

import java.util.List;


@Repository
public interface PatientRepository  extends MongoRepository<Patient, ObjectId> {
    Patient findByEmail(String email);
    List<Patient> findPatientByNameContains(String name);//Will be Request param and find any name whe any latter of name thi method will be work.


}
