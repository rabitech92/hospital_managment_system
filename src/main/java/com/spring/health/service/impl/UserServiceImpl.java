package com.spring.health.service.impl;

import com.spring.health.Dto.LoginDto;
import com.spring.health.Dto.RegisterDto;
import com.spring.health.model.User;
import com.spring.health.repository.UserRepository;
import com.spring.health.service.EmailService;
import com.spring.health.service.UserService;
import com.spring.health.util.EmailUtil;
import com.spring.health.util.OtpUtil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
//  private final EmailService emailService;
    private OtpUtil otpUtil;
    private EmailUtil emailUtil;

    public UserServiceImpl(UserRepository userRepository, OtpUtil otpUtil, EmailUtil emailUtil) {
        this.userRepository = userRepository;

        this.otpUtil = otpUtil;
        this.emailUtil = emailUtil;
    }

    @Override
    public String login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(
                        () -> new RuntimeException("User not found with this email: " + loginDto.getEmail()));
        if (!loginDto.getPassword().equals(user.getPassword())) {
            return "Password is incorrect";
        } else if (!user.isVerified()) {
            return "your account is not verified";
        }
        return "Login successful";
    }


    @Override
    public String regenerateOtp(String email) {
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
        return "Email sent... please verify account within 1 minute";
    }

    @Override
    public String verifyAccount(String email, String otp) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
        if (user.getOtp().equals(otp) && Duration.between(user.getOtpGeneratedTime(),
                LocalDateTime.now()).getSeconds() < (1 * 60)) {
            user.setVerified(true);
            userRepository.save(user);
            return "OTP verified you can login";
        }
        return "Please regenerate otp and try again";
    }

    @Override
    public String register(RegisterDto registerDto) {
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
        return "User registration successful";
    }
//
//    @Override
//    public User registerUser(String email, String password) {
//        User newUser = new User();
//        newUser.setEmail(email);
//        newUser.setPassword(password);
//        newUser.setVerified(false);
//
//        // Generate OTP
//        String otp = generateOTP();
//        newUser.setOtp(otp);
//        newUser.setOtpExpiration(LocalDateTime.now().plusMinutes(15)); // OTP expires in 15 minutes
//
//        // Save the user entity
//        User savedUser = userRepository.save(newUser);
//
//        // Send verification email
//        emailService.sendVerificationEmail(savedUser);
//
//        return savedUser;
//    }
//
//    @Override
//    public boolean verifyEmail(String email, String otp) {
//        User user = userRepository.findByEmail(email);
//
//        if (user != null && isOTPValid(user, otp)) {
//            user.setVerified(true);
//            user.setOtp(null);
//            user.setOtpExpiration(null);
//            userRepository.save(user);
//            return true;
//        }
//
//        return false;
//    }
//
//
//    public boolean isOTPValid(User user, String otp) {
//        return user.getOtp() != null &&
//                user.getOtpExpiration() != null &&
//                user.getOtp().equals(otp) &&
//                user.getOtpExpiration().isAfter(LocalDateTime.now());
//    }
//
//
//    public String generateOTP() {
//        int otpLength = 6;
//        String allowedChars = "0123456789";
//        StringBuilder otp = new StringBuilder(otpLength);
//        Random random = new Random();
//        for (int i = 0; i < otpLength; i++) {
//            int randomIndex = random.nextInt(allowedChars.length());
//            char randomChar = allowedChars.charAt(randomIndex);
//            otp.append(randomChar);
//        }
//        return "Otp already  send your email";
//    }



}
