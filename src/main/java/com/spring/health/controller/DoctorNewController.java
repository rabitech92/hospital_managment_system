package com.spring.health.controller;

import com.spring.health.Dto.DoctorDto;
import com.spring.health.exception.DoctorException;
import com.spring.health.service.DoctorService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/new-doctor")
@RequiredArgsConstructor
public class DoctorNewController {

//    @Resource(name = "doctorServiceNewImpl")
    @Qualifier("doctorServiceNewImpl")
    private final DoctorService doctorService;


    @PostMapping("/create")
    public DoctorDto createDoctor(@RequestBody  DoctorDto doctorDto) throws DoctorException {
        return doctorService.createDoctor(doctorDto);
    }

    @GetMapping("/{id}")
    public DoctorDto getById(@PathVariable ObjectId id) throws DoctorException {
        return doctorService.getDoctorById(id);
    }
}
