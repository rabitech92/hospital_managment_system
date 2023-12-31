package com.spring.health.service.impl;

import com.spring.health.Dto.LoginDto;
import com.spring.health.Dto.RegisterDto;
import com.spring.health.Dto.Response;
import com.spring.health.mapper.UserMapper;
import com.spring.health.model.User;
import com.spring.health.repository.UserRepository;
import com.spring.health.service.EmailService;
import com.spring.health.service.UserService;
import com.spring.health.util.EmailUtil;
import com.spring.health.util.OtpUtil;
import com.spring.health.util.ResponseBuilder;
import jakarta.mail.MessagingException;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final EmailService emailService;
    private final OtpUtil otpUtil;
    private final EmailUtil emailUtil;
    private final UserMapper userMapper;


    public UserServiceImpl(UserRepository userRepository, EmailService emailService, UserMapper userMapper, OtpUtil otpUtil, EmailUtil emailUtil) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.userMapper=userMapper;
        this.otpUtil = otpUtil;
        this.emailUtil = emailUtil;
    }

    @Override
    public Response login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found with this email: " + loginDto.getEmail()));
        if (!loginDto.getPassword().equals(user.getPassword())) {
            return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND,"Password is incorrect");
        } else if (user.isVerified()) {
            return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED,"Login successful",userMapper.toDto(user));
        }
        return null;
    }


    @Override
    public Response regenerateOtp(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
        String otp = otpUtil.generateOtp();
        try {
            emailUtil.sendOtpEmail(email, otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send otp please try again");
        }
        user.setOtp(otp);
        user.setOtpGeneratedTime(LocalDateTime.now());
        userRepository.save(user);
        return ResponseBuilder.getFailureResponse(HttpStatus.OK,"Email sent... please verify account within 1 minute");
    }

    @Override
    public Response verifyAccount(String email, String otp) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
        if (user.getOtp().equals(otp) && Duration.between(user.getOtpGeneratedTime(),
                LocalDateTime.now()).getSeconds() < (5 * 60)) {
            user.setVerified(true);
            userRepository.save(user);
            return ResponseBuilder.getSuccessResponse(HttpStatus.OK,"OTP verified you can login",userMapper.toDto(user));
        }
        return ResponseBuilder.getSuccessResponse(HttpStatus.OK,"Please regenerate otp and try again",userMapper.toDto(user));
    }

    @Override
    public Response register(RegisterDto registerDto) {
        String otp = otpUtil.generateOtp();
        try {
            emailUtil.sendOtpEmail(registerDto.getEmail(), otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send otp please try again");
        }
        User user = new User();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(registerDto.getPassword());
        user.setOtp(otp);
        user.setOtpGeneratedTime(LocalDateTime.now());
        userRepository.save(user);
        return ResponseBuilder.getSuccessResponse(HttpStatus.OK,"User registration successful",userMapper.toRegistrationDto(user));
    }


}
