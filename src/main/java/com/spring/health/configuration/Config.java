package com.spring.health.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.spring.health")
public class Config {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
