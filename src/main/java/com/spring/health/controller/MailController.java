package com.spring.health.controller;

import com.spring.health.Dto.MailSenderDto;
import com.spring.health.model.MailSender;
import com.spring.health.service.MailService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class MailController {

    private final MailService service;
    public MailController(MailService service) {
        this.service = service;
    }

    @PostMapping("/send/{email}")
    public MailSenderDto sendMail(@PathVariable String email, @RequestBody MailSender mailSender ){
       return  service.sendMail(email,mailSender);
    }
}
