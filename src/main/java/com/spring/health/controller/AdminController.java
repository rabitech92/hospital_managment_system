package com.spring.health.controller;

import com.spring.health.Dto.DoctorDto;
import com.spring.health.exception.DoctorException;
import com.spring.health.exception.LoginException;
import com.spring.health.service.PatientService;
import com.spring.health.service.AdminDoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
@RequiredArgsConstructor
public class AdminController {

    private final PatientService patientService;
    private final AdminDoctorService adminDoctorService;

    public DoctorDto rigisterDoctor(@RequestParam String key, @RequestBody DoctorDto DoctorDto)throws DoctorException, LoginException {
        return null;
    }
}
