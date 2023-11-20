package com.spring.health.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
@ToString
public class Appointment {
    private ObjectId id;
    private Patient patient;
    private LocalDateTime appointmentDateAndTime;
    private Doctor doctor;
    @JsonIgnore
    private Review review;

}
