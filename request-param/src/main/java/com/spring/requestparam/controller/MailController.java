package com.spring.requestparam.controller;


import com.spring.requestparam.dto.MailSenderDto;
import com.spring.requestparam.model.SenderMail;
import com.spring.requestparam.service.MailService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class MailController {

    private final MailService service;
    public MailController(MailService service) {
        this.service = service;
    }

    @PostMapping("/send/{email}")
    public MailSenderDto
    sendMail(@PathVariable String email, @RequestBody SenderMail senderMail){
       return  service.mailSend(email, senderMail);
    }
}
