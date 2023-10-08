package com.spring.health.model;

import com.spring.health.enums.Status;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Document(collection = "patients")
public class Patient extends BaseClass{

	private String name;
	private int age ;
	private String gender;
	private String address;
	private String nid;
	private Status status;
	@DBRef
	private Doctor doctor;
	@DBRef
	private Appointment appointment;

}
