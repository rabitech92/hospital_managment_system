package com.spring.health.controller;

import com.spring.health.Dto.PatientDto;
import com.spring.health.Dto.PatientReqDto;
import com.spring.health.exception.PatientException;
import com.spring.health.model.Patient;
import com.spring.health.service.PatientService;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping
public class PatientController {

    private final PatientService patientService;
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }
    @PostMapping
    public PatientDto save(@RequestBody Patient patient) throws PatientException {
        return patientService.create(patient);
    }
    @GetMapping
    public List<PatientDto> getAll(){
        return patientService.getAll();
    }
    @PutMapping("/{id}")
    public PatientDto update(@PathVariable ObjectId id,@RequestBody PatientReqDto payload){
        return patientService.update(id,payload);

    }
    @GetMapping("/{id}")
    public PatientDto getById(@PathVariable ObjectId id){
        return patientService.getById(id);
    }

    @DeleteMapping("/{id}")
    public PatientDto delete(@PathVariable ObjectId id){
        return patientService.delete(id);
    }
}
