package com.spring.health.service.impl;

import com.spring.health.Dto.DoctorDto;
import com.spring.health.Dto.Response;
import com.spring.health.enums.Status;
import com.spring.health.exception.DoctorException;
import com.spring.health.mapper.DoctorMapper;
import com.spring.health.model.Doctor;
import com.spring.health.repository.DoctorRepository;
import com.spring.health.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorServiceNewImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;
    private final DoctorMapper doctorMapper;

    @Override
    public DoctorDto createDoctor(DoctorDto doctorDto) throws DoctorException{
        Optional<Doctor> doctor = doctorRepository.findByMobileNo(doctorDto.getMobileNo());
        if (doctor.isEmpty()){
            doctorDto.setDuration(doctorDto.getEndDateCount() - doctorDto.getStartDateCount());
            Doctor doctorSave = convertToEntity(doctorDto);
            doctorSave = doctorRepository.save(doctorSave);
            return convertEntityToDto(doctorSave);
        }else{
            throw  new DoctorException("This Phone Number already save here "+doctorDto.getMobileNo());
        }
    }

    @Override
    public List<DoctorDto> findAllDoctors() {
        List<Doctor> doctorList = doctorRepository.findAll();
        return doctorList.stream().map(doctor -> convertEntityToDto(doctor)).collect(Collectors.toList());
    }

    @Override
    public DoctorDto getDoctorById(ObjectId id) throws DoctorException {
        Doctor doctor = doctorRepository.findById(id).get();
        DoctorDto doctorDto = modelMapper.map(doctor,DoctorDto.class);
        if (doctor!=null){
            return doctorDto;
        }
        throw new DoctorException("No Doctor this id  ");
    }

    @Override
    @Transactional
    public void deleteDoctor(ObjectId id) throws DoctorException {
       Doctor doctor = doctorRepository.findById(id).orElseThrow();
       doctor.setStatus(Status.DELETE);
       doctorRepository.save(doctor);
       throw new DoctorException("Doctor Deleted this id " + id);
    }

    @Override
    public DoctorDto updateAndSaveDoctor(DoctorDto doctorDto, ObjectId id) throws DoctorException {
        Doctor existDoctor = doctorRepository.findById(id).orElse(null);
        if (existDoctor==null){
            throw new DoctorException("Doctor not Found");
        }
        Doctor  updateDoctor = doctorMapper.toEntity(doctorDto,existDoctor);
        updateDoctor = doctorRepository.save(updateDoctor);
         doctorRepository.save(doctorMapper.toEntity(doctorDto,existDoctor));
         return doctorMapper.toDto(updateDoctor);
    }

    @Override
    public DoctorDto saveFile(DoctorDto doctorDto, MultipartFile file, String docName) {
        return null;
    }

    @Override
    public Response loginDoctor(DoctorDto doctorDto) {
        return null;
    }

    private DoctorDto convertEntityToDto(Doctor doctor){
        DoctorDto doctorDto=modelMapper.map(doctor,DoctorDto.class);
        return doctorDto;
    }

    private Doctor convertToEntity(DoctorDto doctorDto){
        Doctor doctor = modelMapper.map(doctorDto,Doctor.class);
        return doctor;
    }
}
