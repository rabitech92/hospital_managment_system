package com.spring.health.service.impl;

import com.spring.health.Dto.*;
import com.spring.health.exception.*;
import com.spring.health.model.Appointment;
import com.spring.health.model.CurrentSession;
import com.spring.health.model.Doctor;
import com.spring.health.model.Patient;
import com.spring.health.repository.AppointmentRepository;
import com.spring.health.repository.DoctorRepository;
import com.spring.health.repository.PatientRepository;
import com.spring.health.repository.SessionRepository;
import com.spring.health.service.PatientService;
import jakarta.mail.MessagingException;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Transactional
public class PatientServiceImpl  implements PatientService {

    public static Map<String, LocalDateTime> myTimeDate = new LinkedHashMap<>();
    public static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;
    private final SessionRepository sessionRepository;
    private final AppointmentRepository appointmentRepository;


    public PatientServiceImpl(PatientRepository patientRepository, DoctorRepository doctorRepository, ModelMapper modelMapper, SessionRepository sessionRepository, AppointmentRepository appointmentRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
        this.sessionRepository = sessionRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public PatientDto create(PatientDto patientDto) throws PatientException {
        Patient patient=patientRepository.findByEmail(patientDto.getEmail());
        if (patient==null){
            patientDto.setType("Patient");
            patientDto.setPassword(bCryptPasswordEncoder.encode(patientDto.getPassword()));
            Patient patient1=modelMapper.map(patientDto,Patient.class);
            patientRepository.save(patient1);
            PatientDto patientDto1=modelMapper.map(patient1,PatientDto.class);
            return patientDto1;
        }else{
            throw new PatientException("Patient Already saved : " + patientDto.getName());
        }
    }

    @Override
    public AppointmentDto bookAppointment(String key, AppointmentDto appointmentDto) throws AppointmentException, LoginException, DoctorException, IOException, TimeDateException, MessagingException {
        CurrentSession currentSession=sessionRepository.findByUuid(key);
        Optional<Patient> patient=patientRepository.findById(currentSession.getUserId());
        synchronized (this){
            if (patient.isPresent()){
                appointmentDto.setPatient(patient.get());
                Doctor doctor=appointmentDto.getDoctor();
                Optional<Doctor> registerDoctor =doctorRepository.findById(doctor.getId());
                if (!registerDoctor.isEmpty()){
                    appointmentDto.setDoctor(registerDoctor.get());
                    getAppointmentDates(registerDoctor.get().getAppointmentFromTime(),registerDoctor.get().getAppointmentToTime());
                    List<Appointment> appointmentList=appointmentDto.getDoctor().getListOfAppointments();
                    boolean time=false;
                    boolean time1=false;
                    for (Appointment eachAppoinment : appointmentList){
                        if (eachAppoinment.getAppointmentDateAndTime().isEqual(appointmentDto.getAppointmentDateAndTime())){
                            time =true;
                             }
                    }
                    for (String str :myTimeDate.keySet()){
                        if (myTimeDate.get(str).equals(appointmentDto.getAppointmentDateAndTime())){
                            time1=true;
                        }
                    }
                    Appointment registerApointment=null;
                    if (!time && time1){
                        Appointment appointment=modelMapper.map(appointmentDto,Appointment.class);
                        registerApointment=appointmentRepository.save(appointment);


                    }



                }

            }

        }

        return null;
    }

    @Override
    public PatientDto update(Patient user, String key) throws PatientException {
        return null;
    }

    @Override
    public PatientDto getPatientByUuid(String uuid) throws PatientException {
        return null;
    }

    @Override
    public PatientDto searchPatient(String email) throws PatientException {
        return null;
    }

    @Override
    public PatientDto deletePatient(String email) throws PatientException {
        return null;
    }

    @Override
    public CurrentSession getCurrentUserByUuid(String uuid) throws LoginException {
        CurrentSession currentUserSession = sessionRepository.findByUuid(uuid);
        if(currentUserSession != null) {
            return currentUserSession;
        }else {
            throw new LoginException("Please enter valid key");
        }
    }



    public PatientDto convertToDto(PatientReqDto patientReqDto){
        PatientDto patientDto=modelMapper.map(patientReqDto,PatientDto.class);
        return  patientDto;
    }


    public static void getAppointmentDates(Integer from, Integer to) throws IOException, TimeDateException{
        myTimeDate.clear();
        if(from == null || to == null) {
            throw new TimeDateException("Please enter valid doctor appointment From to To time");
        }
        FileReader reader = new FileReader("config.properties");
        Properties p = new Properties();
        p.load(reader);
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime tomorrowDateTime =  currentDateTime.plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        for(int i= from; i <= to; i++) {
            String TodaytimeString = null;
            if(!( i >= 10)) {
                TodaytimeString = currentDateTime.toLocalDate() + " 0" + i + ":00";
            }else {
                TodaytimeString = currentDateTime.toLocalDate() + " " + i + ":00";
            }
            LocalDateTime dateTime = LocalDateTime.parse(TodaytimeString, formatter);
            if(currentDateTime.isBefore(dateTime)) {
                myTimeDate.put("today"+i, dateTime);
            }
        }
        for(int i= from; i <= to; i++) {
            String tomorrowTimeString = null;
            if(!( i >= 10)) {
                tomorrowTimeString = tomorrowDateTime.toLocalDate() + " 0" + i + ":00";
            }else {
                tomorrowTimeString = tomorrowDateTime.toLocalDate() + " " + i + ":00";
            }
            LocalDateTime dateTime = LocalDateTime.parse(tomorrowTimeString, formatter);
            // we are checking if time is gone or not if time is gone then don't put in database
            if(currentDateTime.isBefore(dateTime)) {
                myTimeDate.put("tomorrow"+i, dateTime);
            }
        }
    }
}
