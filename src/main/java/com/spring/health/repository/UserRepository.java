package com.spring.health.repository;

import com.spring.health.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
//      User findByEmail(String email);
        Optional<User> findByEmail(String email);
}
