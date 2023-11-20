package com.spring.health.service.impl;

import com.spring.health.Dto.DoctorDto;
import com.spring.health.Dto.Response;
import com.spring.health.enums.Status;
import com.spring.health.exception.DoctorException;
import com.spring.health.model.Doctor;
import com.spring.health.repository.DoctorRepository;
import com.spring.health.service.DoctorService;
import com.spring.health.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Primary
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;


    @Override
    public DoctorDto createDoctor(DoctorDto doctorDto) {
        Doctor doctor = convertToEntity(doctorDto);
        return convertEntityToDto(doctorRepository.save(doctor));
    }

    @Override
    public List<DoctorDto> findAllDoctors() {
        List<Doctor> doctors =doctorRepository.findAll();
        return doctors.stream().map((doctor) ->convertEntityToDto(doctor)).collect(Collectors.toList());
    }

    @Override
    public DoctorDto getDoctorById(ObjectId id) throws DoctorException {
        Doctor doctor = doctorRepository.findById(id).get();
        doctor.setStatus(Status.ACTIVE);
        return convertEntityToDto(doctor);
    }

    @Override
    public void deleteDoctor(ObjectId id) throws DoctorException {
        doctorRepository.deleteById(id);
    }

    @Override
    public DoctorDto updateAndSaveDoctor(DoctorDto doctorDto, ObjectId id) throws DoctorException {
        Doctor doctor = doctorRepository.findById(id).get();
        if (doctor==null){
            throw new DoctorException("No Doctor this Id "+ id +" ");
        }else {

        doctor.setMobileNo(doctorDto.getMobileNo());
        doctor.setName(doctorDto.getName());
        doctor.setEmail(doctorDto.getEmail());
        doctor.setPassword(doctorDto.getPassword());
        doctor.setType(doctorDto.getType());
        doctor.setSpecialty(doctorDto.getSpecialty());
        doctor.setInsuranceAcceptance(doctorDto.getInsuranceAcceptance());
        doctor.setEducation(doctorDto.getEducation());
        doctor.setExperience(doctorDto.getExperience());
        doctor.setStartDateCount(doctorDto.getStartDateCount());
        doctor.setEndDateCount(doctorDto.getEndDateCount());
        doctor.setAppointmentFromTime(doctorDto.getAppointmentFromTime());
        doctor.setAppointmentToTime(doctorDto.getAppointmentToTime());
        return convertEntityToDto(doctorRepository.save(doctor));
        }
    }

    @Override
    public Response loginDoctor(DoctorDto doctorDto) {
        Doctor doctor= doctorRepository.findByEmail(doctorDto.getEmail());
        if (doctor !=null)
            return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "Login Successfully", doctorDto.getEmail());
        return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND,"No Doctor As " +doctorDto.getName());
    }

    @Override
    public DoctorDto saveFile(DoctorDto doctorDto, MultipartFile file, String docName) {
        return null;
    }

    private DoctorDto convertEntityToDto(Doctor doctor){
        return modelMapper.map(doctor,DoctorDto.class);
    }

    private Doctor convertToEntity(DoctorDto doctorDto){
        return modelMapper.map(doctorDto,Doctor.class);
    }

}
