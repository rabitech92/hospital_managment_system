package com.spring.health.controller;

import com.spring.health.Dto.DoctorDto;
import com.spring.health.Dto.Response;
import com.spring.health.exception.DoctorException;
import com.spring.health.service.DoctorService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {


    private final DoctorService doctorServiceImpl;

    @PostMapping("/save")
    public DoctorDto saveDoctor(@RequestBody DoctorDto doctorDto) throws DoctorException {
        return doctorServiceImpl.createDoctor(doctorDto);
    }

    @GetMapping("/get-all")
    public List<DoctorDto> getAll() throws DoctorException {
        return doctorServiceImpl.findAllDoctors();
    }

    @GetMapping("/{id}")
    public  DoctorDto getById(@PathVariable("id") ObjectId id) throws DoctorException {
        return doctorServiceImpl.getDoctorById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable ObjectId id) throws DoctorException {
        doctorServiceImpl.deleteDoctor(id);
        throw new DoctorException("Doctor delete this  id" +id+" ");
    }

    @PutMapping("/{id}")
    public DoctorDto updateDoctor(@RequestBody DoctorDto doctorDto, @PathVariable ObjectId id) throws DoctorException {
        return doctorServiceImpl.updateAndSaveDoctor(doctorDto, id);
    }

    public Response login(@RequestBody DoctorDto doctorDto){
        return doctorServiceImpl.loginDoctor(doctorDto);
    }

}
