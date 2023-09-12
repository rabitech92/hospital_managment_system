package com.spring.health.model;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection ="doctor")
public class Doctor extends BaseClass {

	private String name;
	private String email;
	private String password;
}
