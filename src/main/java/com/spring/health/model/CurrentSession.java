package com.spring.health.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nonapi.io.github.classgraph.json.Id;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CurrentSession {
	
	@Id
	private ObjectId userId;
	
	private String uuid;
	
	private String userType;
	
	private LocalDateTime localDateTime;


}
