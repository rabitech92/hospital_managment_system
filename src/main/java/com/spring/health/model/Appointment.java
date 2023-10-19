package com.spring.health.model;



import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
@ToString
public class Appointment extends BaseClass{

    private Patient patient;
    private LocalDateTime appointmentDateAndTime;
    private Doctor doctor;

}
