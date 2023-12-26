//package com.spring.health.service;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.spring.health.Dto.RegisterDto;
//import com.spring.health.model.User;
//import com.spring.health.repository.UserRepository;
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.Optional;
//
//@Component
//@RequiredArgsConstructor
//public class MetaData {
//
//    private final ObjectMapper mapper;
//    private final UserService userService;
//    private final UserRepository userRepository;
//
//    @PostConstruct
//    public void addUser(){
//        InputStream jsonFileInputStream = getClass().getResourceAsStream(
//                "/static.json/json/user_admin.json");
//        assert jsonFileInputStream != null;
//        BufferedReader reader = new BufferedReader(new InputStreamReader(jsonFileInputStream));
//        RegisterDto postUser =mapper.readValue(reader, new TypeReference<RegisterDto>() {
//        });
//        userRepository.save(ToEntity(postUser));
//
//    }
//
//    private User ToEntity(RegisterDto registerDto){
//        User user = new User();
//        user.setName(registerDto.getName());
//        user.setEmail(registerDto.getEmail());
//        user.setOtp(registerDto.getPassword());
//        return user;
//    }
//
//}
