package com.postconstruct.model;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User userSave(User user){
        return userRepository.save(user);
    }
}
