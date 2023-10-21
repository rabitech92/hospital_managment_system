package com.spring.health.controller;

import com.spring.health.Dto.LoginDto;
import com.spring.health.exception.LoginException;
import com.spring.health.model.LoginUUIDKey;
import com.spring.health.service.PatientAndAdminLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class LoginController {

    private final PatientAndAdminLoginService patientAndAdminLoginService;

    @PostMapping("/login-patient")
    private LoginUUIDKey loginPatient(@RequestBody LoginDto loginDto) throws LoginException {
        LoginUUIDKey loginUUIDKey=patientAndAdminLoginService.logIntoAccount(loginDto);
       return loginUUIDKey;
    }
}
