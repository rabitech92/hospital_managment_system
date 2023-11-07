package com.spring.health.controller;

import com.spring.health.Dto.DoctorDto;
import com.spring.health.Dto.Response;
import com.spring.health.exception.DoctorException;
import com.spring.health.service.DoctorService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public DoctorDto getById(@PathVariable ObjectId id) throws DoctorException {
        return doctorService.getDoctorById(id);
    }
}
