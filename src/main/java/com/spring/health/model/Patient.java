package com.spring.health.model;

import com.spring.health.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "patients")
public class Patient extends BaseClass{

	private String name;
	private int age ;
	private String gender;
	private String address;
	private String nid;
	private Status status;
	private Doctor doctor;

}
