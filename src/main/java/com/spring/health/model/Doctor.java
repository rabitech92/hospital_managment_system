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
public class Doctor extends BaseClass {
	private String name;
	private String email;
	private String password;
}
