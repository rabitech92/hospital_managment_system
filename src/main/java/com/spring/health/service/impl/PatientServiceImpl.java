package com.spring.health.service.impl;

import com.spring.health.Dto.AppointmentDto;
import com.spring.health.Dto.DoctorDto;
import com.spring.health.Dto.PatientDto;
import com.spring.health.Dto.PatientReqDto;
import com.spring.health.exception.AppointmentException;
import com.spring.health.exception.DoctorException;
import com.spring.health.exception.LoginException;
import com.spring.health.exception.PatientException;
import com.spring.health.model.*;
import com.spring.health.repository.AppointmentRepository;
import com.spring.health.repository.DoctorRepository;
import com.spring.health.repository.PatientRepository;
import com.spring.health.repository.SessionRepository;
import com.spring.health.service.MailService;
import com.spring.health.service.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {


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
    public PatientDto patientCreate(PatientDto patientDto) throws PatientException {
        Patient patient = patientRepository.findByEmail(patientDto.getEmail());
        if (patient == null) {

            patientDto.setType("Patient");
            patientDto.setPassword(bCryptPasswordEncoder.encode(patientDto.getPassword()));
            Patient patient1 = modelMapper.map(patientDto, Patient.class);
            patientRepository.save(patient1);
            PatientDto patientDto1 = modelMapper.map(patient1, PatientDto.class);
            return patientDto1;
        } else {
            throw new PatientException("Patient Already saved : " + patientDto.getName());
        }
    }

    @Override
    public AppointmentDto bookAppointment(String key, AppointmentDto appointmentDto) throws AppointmentException, LoginException, DoctorException {
        CurrentSession currentSession = sessionRepository.findByUuid(key);
        Optional<Patient> registerPatient = patientRepository.findById(currentSession.getUserId());
        if (registerPatient.isPresent()) {
            appointmentDto.setPatient(registerPatient.get());
            Doctor doctor = appointmentDto.getDoctor();
            Optional<Doctor> registerDoctor = doctorRepository.findById(doctor.getId());
            if (!registerDoctor.isEmpty()) {
                appointmentDto.setDoctor(registerDoctor.get());
                boolean appoint = false;
                boolean appoint1 = false;
                Appointment registerAppointment = null;
                if (!appoint && appoint1) {
                    Appointment appointment1 = modelMapper.map(appointmentDto, Appointment.class);
                    registerAppointment = appointmentRepository.save(appointment1);
                    mailSender.setMessage("Dear Sir/Ma'am, \n You have booked an appointment with " + registerAppointment.getDoctor().getName() +
                            ". Please make sure to join on time. If you want to call a doctor please contact " + registerAppointment.getDoctor().getMobileNo() + "\n"
                            + "\n"
                            + "Appointment Id: " + registerAppointment.getId() + "\n"
                            + "Doctor specialty: " + registerAppointment.getDoctor().getSpecialty() + "\n"
                            + "Doctor education: " + registerAppointment.getDoctor().getEducation() + "\n"
                            + "Doctor experience: " + registerAppointment.getDoctor().getExperience() + "\n"
                            + "\n"
                            + "Thanks and Regards \n"
                            + "Appointment Booking Application");
                    mailSender.setSubject("You have successfully book appointment at " + registerAppointment.getAppointmentDateAndTime());
                    PatientServiceImpl patientService = new PatientServiceImpl(mailSender, mailService, appointment);
                } else {
                    throw new AppointmentException("This time or date already booked or please enter valid appointment time and date " + appointment.getAppointmentDateAndTime());
                }
                registerDoctor.get().getListOfAppointments().add(registerAppointment);
                doctorRepository.save(registerDoctor.get());
                registerPatient.get().getListOfAppointments().add(registerAppointment);
                patientRepository.save(registerPatient.get());
                AppointmentDto appointmentDto1 = modelMapper.map(registerAppointment, AppointmentDto.class);
                return appointmentDto1;
            } else {
                throw new DoctorException("Please enter valid doctors details or doctor not present with this id " + doctor.getId());
            }
        } else {
            throw new LoginException("Please enter valid key");
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

    /*------------search API to hold name, email,nid,age------------------*/
    @Override
    public List<PatientDto> search(String name, String email, String nid, int[] age) {
        List<Patient> patientList = patientRepository.findAll();
        // Initialize a list to hold the resulting DTOs
        List<PatientDto> patientDtoList = new ArrayList<>();
        // Iterate over each patient and map it to a DTO
        for (Patient patient : patientList) {
            PatientDto patientDto = modelMapper.map(patient, PatientDto.class);
            // Apply filters
            if ((name == null || patientDto.getName().toLowerCase().contains(name.toLowerCase()))
                    && (email == null || patientDto.getEmail().toLowerCase().contains(email.toLowerCase()))
                    && (nid == null || patientDto.getNid().toLowerCase().contains(nid.toLowerCase()))
                    && (age == null || (patientDto.getAge() >= age[0] && patientDto.getAge() <= age[1]))) {
                patientDtoList.add(patientDto);
            }
        }

        return patientDtoList;
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
        if (currentUserSession != null) {
            return currentUserSession;
        } else {
            throw new LoginException("Please enter valid key");
        }
    }


    public PatientDto convertToDto(PatientReqDto patientReqDto) {
        PatientDto patientDto = modelMapper.map(patientReqDto, PatientDto.class);
        return patientDto;
    }


    public List<Patient> searchaPatient(String name) {
        return patientRepository.findPatientByNameContains(name);
    }


}
