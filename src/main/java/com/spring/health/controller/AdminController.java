package com.spring.health.controller;

import com.spring.health.Dto.DoctorDto;
import com.spring.health.exception.DoctorException;
import com.spring.health.exception.LoginException;
import com.spring.health.mapper.DoctorMapper;
import com.spring.health.model.CurrentSession;
import com.spring.health.model.Doctor;
import com.spring.health.service.PatientAndAdminLoginService;
import com.spring.health.service.PatientService;
import com.spring.health.service.AdminDoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping
@RestController
@RequiredArgsConstructor
public class AdminController {

    private final PatientService patientService;
    private final AdminDoctorService adminDoctorService;
    private final PatientAndAdminLoginService patientAndAdminLoginService;
    private final DoctorMapper doctorMapper;

    @PostMapping("/doctorRegister")
    public DoctorDto rigisterDoctor(@RequestParam String key, @RequestBody DoctorDto doctorDto)throws DoctorException, LoginException {
        if (patientAndAdminLoginService.checkUserLoginOrNot(key)){
            CurrentSession currentSession =patientService.getCurrentUserByUuid(key);
            if (!currentSession.getUserType().equals("admin")){
                throw new LoginException("Please Login as Admin");
            }
            if (doctorDto !=null){
                adminDoctorService.registerDoctor(doctorDto);
                return doctorDto;
            }
            else{
                throw new DoctorException("Please enter valid doctor details");
            }
        }
        else {
            throw new LoginException("Please enter valid key.");
        }
    }



}
