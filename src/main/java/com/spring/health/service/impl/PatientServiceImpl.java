package com.spring.health.service.impl;

import com.spring.health.Dto.DoctorDto;
import com.spring.health.Dto.PatientDto;
import com.spring.health.Dto.PatientReqDto;
import com.spring.health.exception.PatientException;
import com.spring.health.model.Doctor;
import com.spring.health.model.Patient;
import com.spring.health.repository.DoctorRepository;
import com.spring.health.repository.PatientRepository;
import com.spring.health.service.PatientService;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PatientServiceImpl  implements PatientService,Runnable {


    public static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());

    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private ModelMapper modelMapper;


    public PatientServiceImpl(PatientRepository patientRepository, DoctorRepository doctorRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
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
    public PatientDto update(ObjectId id,PatientReqDto patientReqDto) {
        Patient patient=checkAndGet(id);
        if (patient!=null){
            patient.setName(patientReqDto.getName());
            patient.setAge(patientReqDto.getAge());
            patient.setGender(patientReqDto.getGender());
            patient.setAddress(patientReqDto.getAddress());
            patient.setNid(patientReqDto.getNid());
            patient.setDoctor(patientReqDto.getDoctor());
            patient=modelMapper.map(patientReqDto, Patient.class);
            Doctor doctor=doctorRepository.save(patientReqDto.getDoctor());
            patient=patientRepository.save(patient);
           return convertToDto(patient);
        }
        return null;
    }

    @Override
    public PatientDto getById(ObjectId id) {
        try {
            Patient patient=checkAndGet(id);
            return convertToDto(patient);
        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage(),ex);
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
        DoctorDto doctorDto  =modelMapper.map(patient.getDoctor(),DoctorDto.class);
        result.setDoctor(doctorDto);
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

    }
}
