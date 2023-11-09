package com.spring.health.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.health.enums.Status;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Document(collection ="doctors")
public class Doctor extends BaseClass{


	private String mobileNo;
	private String name;
	private String email;
	private String password;
	private String type;
	private String specialty;
	private Boolean insuranceAcceptance;
	private String education;
	private String experience;
	private int startDateCount;
	private int endDateCount;
	private int duration;
	private Status status;
	@JsonIgnore
	private List<Review> listOfReviews = new ArrayList<>();
	private String doctorImg;
	private Boolean validDoctor = true;
	@JsonIgnore
	List<Message> listOfMessage = new ArrayList<>();
	@JsonIgnore
	List<Appointment> listOfAppointments = new ArrayList<>();
	private Integer appointmentFromTime;
	private Integer appointmentToTime;
}
