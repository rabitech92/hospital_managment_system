package com.spring.health.Dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UserDto {

    private String name;
    private String email;
    private String password;
}
