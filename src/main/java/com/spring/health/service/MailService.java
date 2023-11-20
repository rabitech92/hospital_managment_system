package com.spring.health.service;

import com.spring.health.Dto.MailSenderDto;
import com.spring.health.model.MailSender;


public interface MailService {
    MailSenderDto sendMail(String mail, MailSender mailSender);
}
