package com.spring.health.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.health.enums.Status;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Document(collection = "patients")
public class Patient extends BaseClass{

	private String name;
	private String email;
	private String password;
	private int age ;
	private String gender;
	private String address;
	private String nid;
	private String type;


	@JsonIgnore
	private Doctor doctor;
	@JsonIgnore
	List<Appointment> listOfAppointments = new ArrayList<>();
	@JsonIgnore
	List<Review> listReviews = new ArrayList<>();
	@JsonIgnore
	List<Message> listOfMessage = new ArrayList<>();
}
