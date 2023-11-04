package com.spring.health.repository;



import com.spring.health.model.PrescriptionSettingsInfo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionSettingsRepository extends MongoRepository<PrescriptionSettingsInfo, ObjectId> {
  List<PrescriptionSettingsInfo> findAllByActiveStatus(Integer id);
}
