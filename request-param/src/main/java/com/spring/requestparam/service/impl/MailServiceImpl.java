package com.spring.requestparam.service.impl;


import com.spring.requestparam.dto.MailSenderDto;
import com.spring.requestparam.model.SenderMail;
import com.spring.requestparam.service.MailService;
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
    public MailSenderDto mailSend(String email, SenderMail senderMail) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(formMail);
        simpleMailMessage.setTo(email);
        simpleMailMessage.setText(senderMail.getMessage());
        simpleMailMessage.setSubject(senderMail.getSubject());
        javaMailSender.send(simpleMailMessage);
        return toDto(senderMail);
    }

    private MailSenderDto toDto(SenderMail senderMail){
        MailSenderDto mailSenderDto=modelMapper.map(senderMail,MailSenderDto.class);
        return mailSenderDto;
    }
}
