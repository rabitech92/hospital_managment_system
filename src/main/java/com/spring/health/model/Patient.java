package com.spring.health.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@Document(collection = "patients")
public class Patient extends BaseClass{

	private String name;
	@Email(message = "Email should be a valid email")
	private String email;
	private String password;
	private int age ;
	private String gender;
	private String address;
	@Indexed(useGeneratedName = true)
	private String nid;
	private String type;
	@JsonIgnore
	List<Appointment> listOfAppointments = new ArrayList<>();
	@JsonIgnore
	List<Review> listReviews = new ArrayList<>();
	@JsonIgnore
	List<Message> listOfMessage = new ArrayList<>();


	@JsonIgnore
	private Doctor doctor;
	@JsonIgnore
	private Appointment appointment;

}
