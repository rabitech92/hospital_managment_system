package com.spring.health.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.health.model.Patient;

import java.util.List;

@Repository
public interface PatientRepository  extends JpaRepository<Patient, Long> {
//    @Query("UPDATE Patient p SET p.isDeleted = true WHERE p.id = :id")
//    @Modifying
//    void softDeleteById(@Param("id") Long id);

//    List<Patient> findByDeletedFalse();

}
