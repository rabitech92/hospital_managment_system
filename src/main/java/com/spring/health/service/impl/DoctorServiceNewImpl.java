package com.spring.health.service.impl;

import com.spring.health.Dto.DoctorDto;
import com.spring.health.Dto.Response;
import com.spring.health.exception.DoctorException;
import com.spring.health.model.Doctor;
import com.spring.health.repository.DoctorRepository;
import com.spring.health.service.DoctorService;
import com.spring.health.util.ResponseBuilder;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service("doctorServiceNewImpl")
public class DoctorServiceNewImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    public DoctorServiceNewImpl(DoctorRepository doctorRepository, ModelMapper modelMapper) {
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DoctorDto> findAllDoctors() {
        return null;
    }

    @Override
    public Response saveDoctor(DoctorDto doctorDto) {
        return null;
    }

    @Override
    public Response getDoctor() {
        return null;
    }

    @Override
    public Response getByIdDoctor(ObjectId id) {
        return null;
    }

    @Override
    public Response updateDoctor(DoctorDto doctorDto, ObjectId id) {
        return null;
    }

    @Override
    public Response deleteDoctor(ObjectId id) {
        Doctor doctor = doctorRepository.findById(id).orElse(null);
        if (doctor==null){
            return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Internal Server Problem");
        }
        return ResponseBuilder.getSuccessResponse(HttpStatus.OK,"ok",doctor.getId());
    }

    @Override
    public Response loginDoctor(DoctorDto doctorDto) {
        return null;
    }

    @Override
    public DoctorDto getDoctorDetails(ObjectId id) throws DoctorException {
        return null;
    }

    @Override
    public DoctorDto saveFile(DoctorDto doctorDto, MultipartFile file, String docName) {
        return null;
    }

    @Override
    public DoctorDto createDoctor(DoctorDto doctorDto) throws DoctorException{
        Optional<Doctor> doctor = doctorRepository.findByMobileNo(doctorDto.getMobileNo());
        if (doctor.isEmpty()){
            Doctor doctorSave = convertToEntity(doctorDto);
            doctorSave = doctorRepository.save(doctorSave);
            return convertEntityToDto(doctorSave);
        }else{
            throw  new DoctorException("This Phone Number already save here "+doctorDto.getMobileNo());
        }
    }

    @Override
    public DoctorDto getDoctorById(ObjectId id) throws DoctorException {
        Doctor doctor = doctorRepository.findById(id).get();
        DoctorDto doctorDto = modelMapper.map(doctor,DoctorDto.class);
        if (doctor!=null){
            return doctorDto;
        }
        throw new DoctorException("No Doctor this id. ");
    }

    @Override
    public DoctorDto getDuration(int startTime, int endTime) throws DoctorException {
        return null;
    }

    private int timeDuration(int startTime,int endTime){
        int result = endTime-startTime;
        return result;

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
