package com.spring.health.service.impl;

import com.spring.health.Dto.*;
import com.spring.health.exception.*;
import com.spring.health.model.*;
import com.spring.health.repository.AppointmentRepository;
import com.spring.health.repository.DoctorRepository;
import com.spring.health.repository.PatientRepository;
import com.spring.health.repository.SessionRepository;
import com.spring.health.service.MailService;
import com.spring.health.service.PatientService;
import jakarta.mail.MessagingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional

public class PatientServiceImpl  implements PatientService {

    public static Map<String, LocalDateTime> myTimeDate = new LinkedHashMap<>();
    public static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private MailSender mailSender;
    @Autowired
    private MailService mailService;
    @Autowired
    private Appointment appointment;


    public PatientServiceImpl(MailSender mailSender, MailService mailService, Appointment appointment) {
        this.mailSender = mailSender;
        this.mailService = mailService;
        this.appointment = appointment;
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
    public AppointmentDto bookAppointment(String key, Appointment appointmentDto) throws AppointmentException, LoginException, DoctorException, IOException, TimeDateException, MessagingException {
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
                        mailSender.setMessage("Dear Sir/Ma'am, \n You have booked an appointment with " + registerApointment.getDoctor().getName()+
                                ". Please make sure to join on time. If you want to call a doctor please contact " + registerApointment.getDoctor().getEmail()+"\n"
                                +"\n"
                                +"Appointment ID : "+registerApointment.getId()+"\n"
                                +"Doctor specialty: " +registerApointment.getDoctor().getSpecialty()+"\n"
                                +"Doctor education: " +registerApointment.getDoctor().getEducation()+"\n"
                                +"Doctor experience: " +registerApointment.getDoctor().getExperience()+"\n"
                                +"\n"

                                +"Thanks and Regards \n"
                                +"Appointment Booking Application");

                        mailSender.setSubject("You have successfully book appointment at " +registerApointment.getAppointmentDateAndTime());
                        PatientServiceImpl patientServiceImpl=new PatientServiceImpl(mailSender,mailService,appointment);
                        Thread emailSentThread=new Thread((Runnable) patientServiceImpl);
                        emailSentThread.start();

                    }else{
                        throw new AppointmentException("This time or date already booked or please enter valid appointment time and date " +appointment.getAppointmentDateAndTime());
                    }
                    registerDoctor.get().getListOfAppointments().add(registerApointment);
                    doctorRepository.save(registerDoctor.get());
                    AppointmentDto appointmentDto1=modelMapper.map(registerApointment,AppointmentDto.class);
                    return appointmentDto1;
                }else {
                    throw new DoctorException("Please enter valid doctors details or doctor not present with thid id " +doctor.getId());
                }

            }
            else {
                throw new LoginException("Please enter valid key");
            }

        }

    }

    @Override
    public List<DoctorDto> getAllDoctors() throws DoctorException {
        List<Doctor> doctorList = doctorRepository.findAll();
        if (!doctorList.isEmpty()) {
            doctorList = doctorList.stream().filter(everyDoctor -> everyDoctor.getValidDoctor() == true).collect(Collectors.toList());
            List<DoctorDto> doctorDto = doctorList.stream()
                    .map(doctor -> modelMapper.map(doctor, DoctorDto.class))
                    .collect(Collectors.toList());
            return doctorDto;
        } else {
            throw new DoctorException("No doctors registered. Please contact admin.");
        }
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
            if(currentDateTime.isBefore(dateTime)) {
                myTimeDate.put("tomorrow"+i, dateTime);
            }
        }
    }
}
