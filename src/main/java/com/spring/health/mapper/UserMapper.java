package com.spring.health.mapper;

import com.spring.health.Dto.LoginDto;
import com.spring.health.Dto.RegisterDto;
import com.spring.health.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper{

    public LoginDto toDto(User user){
        LoginDto loginDto=CommonMapper.mapDtoToEntity(user,LoginDto.class);
        return loginDto;
    }
    public RegisterDto toRegistrationDto(User user){
        RegisterDto registerDto =CommonMapper.mapDtoToEntity(user,RegisterDto.class);
        return registerDto;
    }

}
