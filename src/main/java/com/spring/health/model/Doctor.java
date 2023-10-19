package com.spring.health.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Document(collection ="doctors")
<<<<<<< HEAD
public class Doctor extends BaseClass{


	@Pattern(regexp = "^[0-9]{10}$", message = "Please enter valid mobile number")
	private String mobileNo;
	private String password;
=======
public class Doctor extends BaseClass {
>>>>>>> parent of 607c5be (patient service impl get all doctors method)
	private String name;
	private String email;
	private String password;
}
