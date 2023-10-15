package com.spring.health.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Message {
	
	@Id
	private Integer messageId;
	private String messageContent;
	private Integer sender;
	private Integer receiver;

	private Patient patient;
	private Doctor doctor;
	private LocalDateTime messageTimeAndDate;
	
}




