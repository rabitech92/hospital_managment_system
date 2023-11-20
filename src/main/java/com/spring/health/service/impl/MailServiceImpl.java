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

    private final JavaMailSender javaMailSender;
    private final ModelMapper modelMapper;

    @Value("$(spring.mail.username)")
    private String formMail;

    @Override
    public MailSenderDto sendMail(String email, MailSender mailSender) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(formMail);
        simpleMailMessage.setTo(email);
        simpleMailMessage.setText(mailSender.getMessage());
        simpleMailMessage.setSubject(mailSender.getSubject());
        javaMailSender.send(simpleMailMessage);
        return toDto(mailSender);
    }

    private MailSenderDto toDto(MailSender mailSender){
        MailSenderDto mailSenderDto=modelMapper.map(mailSender,MailSenderDto.class);
        return mailSenderDto;
    }

}
