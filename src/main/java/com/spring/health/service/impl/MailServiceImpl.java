package com.spring.health.service.impl;

import com.spring.health.Dto.MailSenderDto;
import com.spring.health.Dto.Response;
import com.spring.health.mapper.MailSenderMapper;
import com.spring.health.model.MailSender;
import com.spring.health.service.MailService;
import com.spring.health.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;
    private final MailSenderMapper mailSenderMapper;

    @Value("$(spring.mail.username)")
    private String formMail;

    @Override
    public void sendMail(String email, MailSenderDto mailSenderDto) {
        SimpleMailMessage simpleMailMessage =new SimpleMailMessage();
        simpleMailMessage.setFrom(formMail);
        simpleMailMessage.setSubject(mailSenderDto.getSubject());
        simpleMailMessage.setText(mailSenderDto.getMessage());
        simpleMailMessage.setTo(email);
        mailSender.send(simpleMailMessage);

    }



}
