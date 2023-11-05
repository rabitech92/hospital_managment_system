package com.spring.health.controller;

import com.spring.health.Dto.DoctorDto;
import com.spring.health.Dto.Response;
import com.spring.health.exception.DoctorException;
import com.spring.health.exception.LoginException;
import com.spring.health.service.DoctorLoginService;
import com.spring.health.service.DoctorService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {


    private final DoctorService doctorService;

    public DoctorController(@Qualifier("doctorServiceImpl")DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public Response getAll(){
        return doctorService.getDoctor();
    }

    public Response login(@RequestBody DoctorDto doctorDto){
        return doctorService.loginDoctor(doctorDto);
    }

    @GetMapping("/{id}")
    public DoctorDto getDoctor(@PathVariable ObjectId id) throws DoctorException, LoginException {
       return doctorService.getDoctorDetails(id);
    }

}
