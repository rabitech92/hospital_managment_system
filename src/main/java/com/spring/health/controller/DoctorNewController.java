package com.spring.health.controller;

import com.spring.health.Dto.DoctorDto;
import com.spring.health.exception.DoctorException;
import com.spring.health.model.Doctor;
import com.spring.health.service.DoctorService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/new-doctor")
@RequiredArgsConstructor
public class DoctorNewController {

    @Resource(name = "doctorServiceNewImpl")
    private final DoctorService doctorServiceNewImpl;


    @PostMapping("/create")
    public DoctorDto createDoctor(@RequestBody  DoctorDto doctorDto) throws DoctorException {
        return doctorServiceNewImpl.createDoctor(doctorDto);
    }

    @GetMapping("/{id}")
    public DoctorDto getById(@PathVariable ObjectId id) throws DoctorException {
        return doctorServiceNewImpl.getDoctorById(id);
    }

    @GetMapping("/all-doctor")
    public List<DoctorDto> allDoctors() throws DoctorException {
        return doctorServiceNewImpl.findAllDoctors();
    }

    @DeleteMapping("/{id}")
    public void doctorDelete(@PathVariable ObjectId id) throws DoctorException {
        doctorServiceNewImpl.deleteDoctor(id);
    }

    @PutMapping("/update/{id}")
    public DoctorDto uptateDoctor(@RequestBody DoctorDto doctorDto,@PathVariable ObjectId id) throws DoctorException {
        return doctorServiceNewImpl.updateAndSaveDoctor(doctorDto,id);
    }
}
