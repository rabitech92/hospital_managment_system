package com.spring.health.service;

import com.spring.health.Dto.LoginDto;
import com.spring.health.Dto.RegisterDto;
import com.spring.health.Dto.Response;
import com.spring.health.model.User;

public interface UserService {
//    public User registerUser(String email, String password);
//    public boolean verifyEmail(String email, String otp);

     Response login(LoginDto loginDto);
     Response regenerateOtp(String email);
     Response verifyAccount(String email, String otp);
     Response register(RegisterDto registerDto);

}
