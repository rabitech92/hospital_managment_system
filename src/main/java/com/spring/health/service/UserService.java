package com.spring.health.service;

import com.spring.health.model.User;
import com.spring.health.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user){
       return userRepository.save(user);
    }

    public User getById(Long id){
        return userRepository.findById(id).get();

    }

}
