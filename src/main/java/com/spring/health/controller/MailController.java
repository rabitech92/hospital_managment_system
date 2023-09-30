package com.spring.health.controller;

import com.spring.health.Dto.MailSenderDto;
import com.spring.health.service.impl.MailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class MailController {

    private final MailServiceImpl service;

    @PostMapping("/send/{email}")
    public void sendMail(@PathVariable String email, @RequestBody MailSenderDto mailSenderDto ){
         service.sendMail(email,mailSenderDto);



    }
}
