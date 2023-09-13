package com.spring.health.service.impl;

import com.spring.health.Dto.DoctorDto;
import com.spring.health.Dto.PatientDto;
import com.spring.health.Dto.PatientReqDto;
import com.spring.health.model.Doctor;
import com.spring.health.model.Patient;
import com.spring.health.repository.DoctorRepository;
import com.spring.health.repository.PatientRepository;
import com.spring.health.service.PatientService;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PatientServiceImpl  implements PatientService {
    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private ModelMapper modelMapper;

    public PatientServiceImpl(PatientRepository patientRepository, DoctorRepository doctorRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public PatientDto create(PatientReqDto patientReqDto) {
        try {
                if (doctorRepository.existsByEmailAndIdNotIn(patientReqDto.getDoctor().getEmail(),patientReqDto.getDoctor().getId()))
            {
                throw new RuntimeException("Email Must be Unique " + patientReqDto.getDoctor().getEmail() +" is already taken bu another Doctor !");
            }
            Patient patient=modelMapper.map(patientReqDto,Patient.class);
            Doctor doctor = modelMapper.map(patientReqDto,Doctor.class);
            patient.setDoctor(doctor);
            patient=patientRepository.save(patient);
            return convertToDto(patient);
        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage(), ex);
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
    public PatientDto update(PatientReqDto patientReqDto) {
        try {
           if (doctorRepository.existsByEmailAndIdNotIn(patientReqDto.getDoctor().getEmail(),patientReqDto.getDoctor().getId())){
               throw new RuntimeException("Email Must br unique. " + patientReqDto.getDoctor().getEmail() +" is already taken by another user !");
            }
            Patient patient=checkAndGet(patientReqDto.getId());
            modelMapper.map(patientReqDto,patient);
            patient=patientRepository.save(patient);
            return convertToDto(patient);
            }catch (Exception ex){
            throw new RuntimeException(ex.getMessage(),ex);
        }
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
        if(!patientOpt.isPresent()){
            throw new RuntimeException("Patient not found with ID: "+ id);
        }
        return patientOpt.get();
    }


}
