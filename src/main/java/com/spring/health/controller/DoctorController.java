package com.spring.health.controller;

import com.spring.health.Dto.Response;
import com.spring.health.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctors")
@AllArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    @GetMapping
    public Response getAll(){
        return doctorService.getDoctor();
    }

}
