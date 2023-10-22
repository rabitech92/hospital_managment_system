package com.spring.health.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	private String type;
	private String password;
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
