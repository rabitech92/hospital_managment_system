package com.spring.health.controller;

import com.spring.health.Dto.DoctorDto;
import com.spring.health.exception.DoctorException;
import com.spring.health.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/new-doctor")
public class DoctorNewController {


    private final DoctorService doctorService;

    public DoctorNewController(@Qualifier("doctorServiceNewImpl")DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/create")
    public DoctorDto createDoctor(@RequestBody  DoctorDto doctorDto) throws DoctorException {
        return doctorService.createDoctor(doctorDto);
    }


}
