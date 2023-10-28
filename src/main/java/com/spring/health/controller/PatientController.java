package com.spring.health.controller;

import com.spring.health.Dto.*;
import com.spring.health.exception.*;
import com.spring.health.model.Appointment;
import com.spring.health.model.Patient;
import com.spring.health.service.DoctorService;
import com.spring.health.service.PatientAndAdminLoginService;
import com.spring.health.service.PatientService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    private final PatientAndAdminLoginService loginService;
    private final DoctorService doctorService;


    @PostMapping
    public PatientDto save(@RequestBody PatientDto patient) throws PatientException {
        return patientService.patientCreate(patient);
    }

    @PutMapping("/updatePatient")
    public PatientDto update(@RequestBody Patient patient,@RequestParam(required = false) String key)throws PatientException{
        return patientService.update(patient,key);
    }

    @GetMapping
    public PatientDto getByUuid(@RequestParam String key) throws PatientException {
        return patientService.getPatientByUuid(key);
    }

    @GetMapping("/email")
    public PatientDto searchPatient(@RequestParam("email") String email) throws PatientException {
       return patientService.searchPatient(email);
    }
    @PostMapping("/bookAppointment")
    public AppointmentDto appoint(@RequestParam String key,@RequestBody AppointmentDto appointmentDto) throws AppointmentException, LoginException, TimeDateException, MessagingException, IOException, DoctorException {
        if (appointmentDto==null){
            throw new AppointmentException("Please enter valid appointment");
        }
        if (loginService.checkUserLoginOrNot(key)){
            AppointmentDto registerAppointment = patientService.bookAppointment(key,appointmentDto);
            return registerAppointment;
        }
        else {
            throw new LoginException("Invalid key or please login first");
        }
    }

    @GetMapping("/DoctorList")
    public List<DoctorDto> getAll() throws DoctorException {
        return patientService.getAllDoctors();
    }

    @PostMapping("/doctorShedule")
    public List<LocalDateTime> doctorShedule(@RequestParam String key, @RequestBody DoctorDto doctorDto) throws LoginException, TimeDateException, IOException, DoctorException {
        if (loginService.checkUserLoginOrNot(key)) {
            List<LocalDateTime> listOfAvailable = doctorService.getDoctorAvailableTimingForBooking(key, doctorDto);
            return listOfAvailable;
        } else {
            throw new LoginException("Invalid key or please login first");
        }
    }
}
