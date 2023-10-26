package com.spring.health.service.impl;

import com.spring.health.Dto.MailSenderDto;
import com.spring.health.model.MailSender;
import com.spring.health.service.MailService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;
    private final ModelMapper modelMapper;

    @Value("$(spring.mail.username)")
    private String formMail;

    @Override
    public MailSenderDto sendMail(String email, MailSender mailSenderDto) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(formMail);
        simpleMailMessage.setSubject(mailSenderDto.getSubject());
        simpleMailMessage.setText(mailSenderDto.getMessage());
        simpleMailMessage.setTo(email);
        mailSender.send(simpleMailMessage);
        return toDto(mailSenderDto);
    }


    private MailSenderDto toDto(MailSender mailSender) {
        MailSenderDto mailSenderDto = modelMapper.map(mailSender, MailSenderDto.class);
        return mailSenderDto;
    }


}
