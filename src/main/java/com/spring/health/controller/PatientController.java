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

    @PutMapping("/updatePatient")
    public PatientDto update(@RequestBody Patient patient,@RequestParam(required = false) String key)throws PatientException{
        return patientService.update(patient,key);
    }

    @GetMapping
    public PatientDto getByUuid(@RequestParam String key) throws PatientException {
        return patientService.getPatientByUuid(key);
    }

    @GetMapping
    public List<PatientDto> getAll() throws PatientException {
        return patientService.getAll();
    }



    @DeleteMapping("/{id}")
    public PatientDto delete(@PathVariable ObjectId id) throws PatientException {
        return patientService.delete(id);
    }

    @GetMapping("/email")
    public PatientDto searchPatient(@RequestParam("email") String email) throws PatientException {
       return patientService.searchPatient(email);
    }
}
