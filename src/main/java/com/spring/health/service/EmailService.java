package com.spring.health.service;

import com.spring.health.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private JavaMailSender javaMailSender;

    public void sendVerificationEmail(User user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Email Verification OTP");
        mailMessage.setText("Your OTP for email verification is: " + user.getOtp());

        javaMailSender.send(mailMessage);
    }
}
