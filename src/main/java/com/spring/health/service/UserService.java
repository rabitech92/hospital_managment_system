package com.spring.health.service;


import com.spring.health.Dto.UserDto;
import com.spring.health.model.User;
import com.spring.health.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    private UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user){
       return userRepository.save(user);
    }

    public User getById(Long id){
        return userRepository.findById(id).get();
    }
    public List<User> getAll(){
        return  userRepository.findAll();
    }

    public User update(User user,Long id){
        User user1=userRepository.findById(id).get();
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        return userRepository.save(user1);
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }



}
