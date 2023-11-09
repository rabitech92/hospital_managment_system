package com.spring.health.controller;

import com.spring.health.Dto.DoctorDto;
import com.spring.health.Dto.Response;
import com.spring.health.exception.DoctorException;
import com.spring.health.exception.LoginException;
import com.spring.health.service.DoctorLoginService;
import com.spring.health.service.DoctorService;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

//  @Resource(name = "doctorServiceImpl")
    @Qualifier("doctorServiceImpl")
    private final DoctorService doctorService;

    @PostMapping("/save")
    public DoctorDto saveDoctor(@RequestBody DoctorDto doctorDto) throws DoctorException {
        return doctorService.createDoctor(doctorDto);
    }

    @GetMapping("/get-all")
    public List<DoctorDto> getAll() throws DoctorException {
        return doctorService.findAllDoctors();
    }

    @GetMapping("/{id}")
    public  DoctorDto getById(@PathVariable("id") ObjectId id) throws DoctorException {
        return doctorService.getDoctorById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable ObjectId id) throws DoctorException {
        doctorService.deleteDoctor(id);
        throw new DoctorException("Doctor delete this  id" +id+" ");
    }

    @PutMapping("/{id}")
    public DoctorDto updateDoctor(@RequestBody DoctorDto doctorDto, @PathVariable ObjectId id) throws DoctorException {
        return doctorService.updateAndSaveDoctor(doctorDto, id);
    }

    public Response login(@RequestBody DoctorDto doctorDto){
        return doctorService.loginDoctor(doctorDto);
    }

}
