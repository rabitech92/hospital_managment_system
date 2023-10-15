package com.spring.health.service.impl;


import com.spring.health.Dto.AppointmentDto;
import com.spring.health.Dto.PatientDto;
import com.spring.health.Dto.PatientReqDto;
import com.spring.health.exception.*;
import com.spring.health.model.*;
import com.spring.health.repository.AppointmentRepository;
import com.spring.health.repository.DoctorRepository;
import com.spring.health.repository.PatientRepository;
import com.spring.health.repository.SessionRepository;
import com.spring.health.service.EmailSenderService;
import com.spring.health.service.PatientService;
import jakarta.mail.MessagingException;
import org.bson.types.ObjectId;
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
public class PatientServiceImpl  implements PatientService,Runnable {

    public static Map<String, LocalDateTime> myTimeDate = new LinkedHashMap<>();
    public static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    EmailSenderService emailSenderService;
    @Autowired
    Appointment savedAppointment;
    @Autowired
    EmailBody emailBody;
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    AppointmentRepository appointmentRepository;




    public PatientServiceImpl(Appointment appointment, EmailSenderService emailSenderService, EmailBody emailBody) {
        this.savedAppointment = appointment;
        this.emailSenderService = emailSenderService;
        this.emailBody = emailBody;
    }


    @Override
    public PatientDto create(Patient patient) throws PatientException{
        Patient patient1=patientRepository.findByEmail(patient.getEmail());
        if (patient1==null){
            patient.setType("Patient");
            patient.setPassword(bCryptPasswordEncoder.encode(patient.getPassword()));
            patientRepository.save(patient);
            return convertToDto(patient);
        }else{
            throw new PatientException("Patient already register with this Email Id "+patient.getEmail());
        }
    }

    @Override
    public PatientDto update(Patient patient, String key) throws PatientException {
        CurrentSession session=sessionRepository.findByUuid(key);
        if (session==null){
            throw new PatientException("Please provide the valid key to update the user");
        }
        if (patient.getId() == session.getUserId()){
            Patient patient1= patientRepository.save(patient);
            return convertToDto(patient1);
        }else {
            throw new PatientException("Invalid user details. Login first");
        }
    }

    @Override
    public List<PatientDto> getAll() {
        try {
        List<Patient> patients= patientRepository.findAll();
        List<PatientDto> patientDtos = patients.stream().map(this::convertToDto).collect(Collectors.toList());
        return patientDtos;
        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }



    @Override
    public PatientDto getPatientByUuid(String uuid) throws PatientException {
       CurrentSession session =sessionRepository.findByUuid(uuid);
       Optional<Patient> patient=patientRepository.findById(session.getUserId());
       if (patient.isPresent()){
           return convertToDto(patient.get());
       }else {
           throw new PatientException("Patient or Admin not present by this uuid " + uuid);
       }
        }


    @Override
    public CurrentSession getCurrentPatientByUuid(String uuid) throws LoginException {
        CurrentSession currentUserSession = sessionRepository.findByUuid(uuid);
        if(currentUserSession != null) {
            return currentUserSession;
        }else {
            throw new LoginException("Please enter valid key");
        }
    }


    public static void getAppointmentDates(Integer from, Integer to) throws IOException, TimeDateException {
        // empty the myTimeDate firstly before putting the new values
        myTimeDate.clear();
        // checking from and to is null or not
        if(from == null || to == null) {
            throw new TimeDateException("Please enter valid doctor appointment From to To time");
        }
        FileReader reader = new FileReader("config.properties");
        Properties p = new Properties();
        p.load(reader);
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime tomorrowDateTime =  currentDateTime.plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        // puting todays dates
        for(int i= from; i <= to; i++) {
            String TodaytimeString = null;
            if(!( i >= 10)) {
                TodaytimeString = currentDateTime.toLocalDate() + " 0" + i + ":00";
            }else {
                TodaytimeString = currentDateTime.toLocalDate() + " " + i + ":00";
            }
            LocalDateTime dateTime = LocalDateTime.parse(TodaytimeString, formatter);
            // we are checking if time is gone or not if time is gone then don't put in database
            // 2023-03-09 01:00
            if(currentDateTime.isBefore(dateTime)) {
                myTimeDate.put("today"+i, dateTime);

            }

        }

        // puting tomorrow dates

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


    @Override
    public AppointmentDto bookAppointment(String key, Appointment appointment) throws AppointmentException, LoginException, DoctorException, IOException, TimeDateException, MessagingException {
        CurrentSession currentPatientSession = sessionRepository.findByUuid(key);
        Optional<Patient> patient = patientRepository.findById(currentPatientSession.getUserId());
        synchronized (this) {
            if(patient.isPresent()) {
                // setting patient in appointment
                appointment.setPatient(patient.get());
                Doctor doctor = appointment.getDoctor();
                Optional<Doctor> registerDoctors = doctorRepository.findById(doctor.getDoctorId());
                if(!registerDoctors.isEmpty()) {
                    // setting doctor in appointment
                    appointment.setDoctor(registerDoctors.get());
                    // check if appointment date and time is available or not
                    // this line generating time dynamically from doctors choice of work.
                    getAppointmentDates(registerDoctors.get().getAppointmentFromTime(),registerDoctors.get().getAppointmentToTime());
                    List<Appointment> listOfAppointment = appointment.getDoctor().getListOfAppointments();
                    Boolean flag1 = false;
                    Boolean flag2 = false;
                    for(Appointment eachAppointment: listOfAppointment) {
                        if(eachAppointment.getAppointmentDateAndTime().isEqual(appointment.getAppointmentDateAndTime())) {
                            flag1 = true;
                        }
                    }

                    // check if given date and time if correct or not
                    for(String str : myTimeDate.keySet()) {
                        if(myTimeDate.get(str).isEqual(appointment.getAppointmentDateAndTime())) {
                            flag2 = true;
                        }
                    }
                    Appointment registerAppointment = null;
                    if(!flag1 && flag2) {
                        registerAppointment = appointmentRepository.save(appointment);
                        ////////////////////////////////
                        emailBody.setEmailBody("Dear Sir/Ma'am, \n You have booked an appointment with " + registerAppointment.getDoctor().getName() +
                                ". Please make sure to join on time. If you want to call a doctor please contact " + registerAppointment.getDoctor().getMobileNo()+"\n"
                                +"\n"
                                +"Appointment Id: " + registerAppointment.getAppointmentId()+"\n"
                                +"Doctor specialty: " + registerAppointment.getDoctor().getSpecialty()+"\n"
                                +"Doctor education: " + registerAppointment.getDoctor().getEducation()+"\n"
                                +"Doctor experience: " + registerAppointment.getDoctor().getExperience() +"\n"
                                +"\n"

                                +"Thanks and Regards \n"
                                +"Appointment Booking Application");

                        emailBody.setEmailSubject("You have successfully book appointment at " + registerAppointment.getAppointmentDateAndTime());
                        PatientServiceImpl patientServiceImpl = new PatientServiceImpl(appointment, emailSenderService, emailBody);
                        Thread emailSentThread = new Thread(patientServiceImpl);
                        /////////////////////////
                        // Multi-Threading
                        emailSentThread.start();
                        ///////////////////////////////
                    }else {

                        throw new AppointmentException("This time or date already booked or please enter valid appointment time and date " + appointment.getAppointmentDateAndTime());

                    }


                    // we can't map appointment object directly because we don't have appointment id in it we have to mapped after saving the
                    // appointment and then we will get the appointment id then it will not generate appointment again. If we mapped the register
                    // appointment.

                    // mapping appointment in doctor and then saving doctor



                    registerDoctors.get().getListOfAppointments().add(registerAppointment);
                    doctorRepository.save(registerDoctors.get());
                    // mapping appointment in patient then saving patient
                    patient.get().getListOfAppointments().add(registerAppointment);
                    patientRepository.save(patient.get());
                    return convertToDto(registerAppointment);
                }else {
                    throw new DoctorException("Please enter valid doctors details or doctor not present with thid id " + doctor.getDoctorId());
                }
            }else {
                throw new LoginException("Please enter valid key");
            }
        }
    }

    @Override
    public List<Appointment> getAllAppointmenPatientWise(String key) throws AppointmentException, PatientException {
        CurrentSession currentPatientSession = sessionRepository.findByUuid(key);
        Optional<Patient> patient = patientRepository.findById(currentPatientSession.getUserId());
        if(patient.get() != null) {
            List<Appointment> listOfAppointments = patient.get().getListOfAppointments();
            if(!listOfAppointments.isEmpty()) {
                return listOfAppointments;
            }else {
                throw new AppointmentException("No appointments found. Please book appointments");
            }
        }else {
            throw new PatientException("Please enter valid patient details");
        }
    }
    @Override
    public Appointment updateAppointment(String key, Appointment newAppointment) throws AppointmentException, PatientException, DoctorException, IOException, TimeDateException {
        CurrentSession currentPatientSession = sessionRepository.findByUuid(key);
        Optional<Patient> patient = patientRepository.findById(currentPatientSession.getUserId());
        if(patient.get() != null) {
            Optional<Appointment> registerAppoinment = appointmentRepository.findById(newAppointment.getAppointmentId());
            Optional<Doctor> registerDoctor = doctorRepository.findById(newAppointment.getDoctor().getDoctorId());
            if(!registerAppoinment.isEmpty()) {
                // check patient updated doctor or not
                Doctor newDoctor = newAppointment.getDoctor();
                Doctor oldDoctor = registerAppoinment.get().getDoctor();
                Boolean patientChangeDoctorOrNot = newDoctor.getDoctorId() == oldDoctor.getDoctorId() ? true : false;
                if(!registerDoctor.isEmpty()) {
                    // patient did not change the doctor now check patient may have change appointment time then check this doctor is
                    // available at that time or not.
                    LocalDateTime newTime = newAppointment.getAppointmentDateAndTime();
                    LocalDateTime oldTime = registerAppoinment.get().getAppointmentDateAndTime();
                    if(!newTime.isEqual(oldTime) || !patientChangeDoctorOrNot) {
                        LocalDateTime presentTime = LocalDateTime.now();
                        // it will going to check patient is updating appointment correctly or not. Patient is changing appointment
                        // when appointment time is left if yes then appointment will not be update at that condition.

                        if(oldTime.isBefore(presentTime) && !newTime.isAfter(presentTime)) {
                            throw new AppointmentException("You can't update the appointment at this time. Your appointment date time left.");
                        }
                        getAppointmentDates(registerDoctor.get().getAppointmentFromTime(),registerDoctor.get().getAppointmentToTime());
                        List<Appointment> listOfAppointment = registerDoctor.get().getListOfAppointments();
                        Boolean flag1 = false;
                        Boolean flag2 = false;
                        for(Appointment eachAppointment: listOfAppointment) {
                            if(eachAppointment.getAppointmentDateAndTime().isEqual(newAppointment.getAppointmentDateAndTime())) {
                                flag1 = true;
                            }
                        }
                        // check if given date and time if correct or not
                        for(String str : myTimeDate.keySet()) {
                            if(myTimeDate.get(str).isEqual(newAppointment.getAppointmentDateAndTime())) {
                                flag2 = true;
                            }
                        }
                        if(!flag1 && flag2) {
                            registerAppoinment.get().getDoctor().getListOfAppointments().remove(newAppointment);
                            appointmentRepository.save(registerAppoinment.get());
                            newAppointment.setDoctor(registerDoctor.get());
                            registerDoctor.get().getListOfAppointments().add(newAppointment);
                            doctorRepository.save(registerDoctor.get());
                            return newAppointment;
                        }else {
                            throw new AppointmentException("This time or date already booked. Please enter valid appointment time and date " + newAppointment.getAppointmentDateAndTime());
                        }
                    }else {
                        throw new AppointmentException("Please update the appointment. You did not update anythings.");
                    }
                }else {
                    throw new DoctorException("No doctor found with this id " + newAppointment.getDoctor().getDoctorId());
                }
            }else {
                throw new AppointmentException("No appointments found. Please book appointments");
            }
        }else {
            throw new PatientException("Please enter valid patient details");
        }
    }

    @Override
    public List<Doctor> getAllDoctors() throws DoctorException {
        List<Doctor> listOfDoctors = doctorRepository.findAll();
        if(!listOfDoctors.isEmpty()) {
            listOfDoctors = listOfDoctors.stream().filter(eachDoctor -> eachDoctor.getValidDoctor() == true).collect(Collectors.toList());
            return listOfDoctors;
        }else {
            throw new DoctorException("No doctors register. Please contact admin.");
        }

    }



    @Override
    public PatientDto delete(ObjectId id) {
        try {
            Patient patient=checkAndGet(id);
            doctorRepository.delete(patient.getDoctor());
            patientRepository.delete(patient);
            return convertToDto(patient);
        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage(),ex);
        }
    }



    private PatientDto convertToDto(Patient patient){
        PatientDto result =modelMapper.map(patient, PatientDto.class);
        return result;
        }

    private AppointmentDto convertToDto(Appointment appointment){
        AppointmentDto result =modelMapper.map(appointment, AppointmentDto.class);
        return result;
    }



    private Patient checkAndGet(ObjectId id){
        Optional<Patient> patientOpt = patientRepository.findById(id);
        if(patientOpt.isEmpty()){
            throw new RuntimeException("Patient not found with ID: "+ id);
        }
        return patientOpt.get();
    }


    @Override
    public void run() {
        try {
            emailSenderService.sendAppointmentBookingMail(savedAppointment.getPatient().getEmail(),emailBody);
        }catch (MessagingException e){
            e.printStackTrace();
        }

    }
}
