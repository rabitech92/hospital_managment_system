package com.spring.requestparam.service;


import com.spring.requestparam.dto.MailSenderDto;
import com.spring.requestparam.model.SenderMail;


public interface MailService {
    MailSenderDto mailSend(String mail, SenderMail senderMail);
}
