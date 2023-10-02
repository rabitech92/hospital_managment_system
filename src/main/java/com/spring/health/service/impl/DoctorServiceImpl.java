package com.spring.health.service.impl;

import com.spring.health.Dto.DoctorDto;
import com.spring.health.Dto.Response;
import com.spring.health.mapper.DoctorMapper;
import com.spring.health.model.Doctor;
import com.spring.health.repository.DoctorRepository;
import com.spring.health.service.DoctorService;
import com.spring.health.util.ResponseBuilder;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;
    private final DoctorMapper doctorMapper;



    @Override
    public List<DoctorDto> findAllDoctors() {
        List<Doctor> doctors =doctorRepository.findAll();
        return doctors.stream().map((doctor) ->convertEntityToDto(doctor)).collect(Collectors.toList());
    }

    @Override
    public Response saveDoctor(DoctorDto doctorDto) {
        Doctor doctor=doctorMapper.toEntity(doctorDto);
        doctorRepository.save(doctor);
        if (doctor!=null){
            return ResponseBuilder.getSuccessResponse(HttpStatus.ACCEPTED,"Doctor's Data save",doctorMapper.toDto(doctor));
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Internal server Error");
    }

    @Override
    public Response getDoctor() {
        List<Doctor> doctorList = doctorRepository.findAll();
        if (doctorList.isEmpty()){
            return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Internal Server error");
        }
        return ResponseBuilder.getSuccessResponse(HttpStatus.FOUND,"All date are shown",doctorMapper.toDtoList(doctorList),doctorList.size());
    }

    @Override
    public Response getByIdDoctor(ObjectId id) {
        Doctor doctor=doctorRepository.findById(id).orElse(null);
        if (doctor==null){
            return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Internal Server Error");
        }
        return ResponseBuilder.getSuccessResponse(HttpStatus.FOUND,"Doctor Found",doctorMapper.toDto(doctor));
    }

    @Override
    public Response updateDoctor(DoctorDto doctorDto,ObjectId id) {
        Doctor existingDoctor=doctorRepository.findById(id).orElse(null);
        if (existingDoctor==null){
            return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND,"Doctor Not Found");
        }
        Doctor updateDoctor =doctorMapper.toEntity(doctorDto,existingDoctor);
        return ResponseBuilder.getSuccessResponse(HttpStatus.OK,"Doctor has been Update",doctorMapper.toDto(updateDoctor));

    }

    @Override
    public Response deleteDoctor(ObjectId id){
        if (!doctorRepository.existsById(id)){
            return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND,"This id " +id+" Not Found");
        }
        doctorRepository.deleteById(id);
        return ResponseBuilder.getSuccessResponse(HttpStatus.OK,"Doctor Id has deleted : ",id);
    }


    private DoctorDto convertEntityToDto(Doctor doctor){
        DoctorDto doctorDto=modelMapper.map(doctor,DoctorDto.class);
        return doctorDto;
    }


}
