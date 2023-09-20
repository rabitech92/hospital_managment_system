package com.spring.health.controller;

import com.spring.health.Dto.AppointmentDto;
import com.spring.health.Dto.Response;
import com.spring.health.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
@AllArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping
    public Response saveAppointment(@RequestBody AppointmentDto appointmentDto){
    return appointmentService.saveAppointment(appointmentDto);
    }

    @GetMapping
    public Response getAllAppointment(){
        return appointmentService.getApointment();
    }

    @GetMapping("/{id}")
    public Response getById(@PathVariable String id){
        return appointmentService.getApointmentById(id);
    }
    @PutMapping("/{id}")
    public Response update(@PathVariable String id,@RequestBody  AppointmentDto appointmentDto){
        return appointmentService.updateApointment(appointmentDto,id);

    }
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable String id){
        return appointmentService.deleteApointment(id);
    }

}
