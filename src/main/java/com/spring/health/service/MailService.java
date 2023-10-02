package com.spring.health.service;

import com.spring.health.Dto.MailSenderDto;


public interface MailService {
    void sendMail(String mail, MailSenderDto mailSenderDto);
}
