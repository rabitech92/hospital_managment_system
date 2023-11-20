package com.spring.health.service;

import com.spring.health.Dto.LoginDto;
import com.spring.health.Dto.RegisterDto;
import com.spring.health.Dto.Response;
import com.spring.health.model.User;

public interface UserService {


     Response login(LoginDto loginDto);
     Response regenerateOtp(String email);
     Response verifyAccount(String email, String otp);
     Response register(RegisterDto registerDto);

}
