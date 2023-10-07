package com.spring.health.service;

import com.spring.health.Dto.LoginDto;
import com.spring.health.Dto.RegisterDto;
import com.spring.health.model.User;

public interface UserService {
//    public User registerUser(String email, String password);
//    public boolean verifyEmail(String email, String otp);

    public String login(LoginDto loginDto);
    public String regenerateOtp(String email);
    public String verifyAccount(String email, String otp);
    public String register(RegisterDto registerDto);

}
