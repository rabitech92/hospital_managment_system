package com.spring.health.configuration;

import com.spring.health.Dto.UserDto;
import com.spring.health.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    public UserDto convertToUserDto(User user){
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(user,UserDto.class);
    }
    public User convertToUser(UserDto userDto){
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(userDto,User.class);
    }
}
