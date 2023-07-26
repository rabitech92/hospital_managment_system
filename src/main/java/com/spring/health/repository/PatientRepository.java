package com.spring.health.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.health.model.Patient;

@Repository
public interface PatientRepository  extends JpaRepository<Patient, Long> {

}
