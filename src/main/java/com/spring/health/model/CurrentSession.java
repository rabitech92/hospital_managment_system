package com.spring.health.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document("currentSession")
@ToString
@Getter
@Setter
public class CurrentSession {
	
	@Id
	private ObjectId userId;
	
	private String uuid;
	
	private String userType;
	
	private LocalDateTime localDateTime;
	
	public CurrentSession() {
	}

	public CurrentSession(ObjectId userId, String uuid, LocalDateTime localDateTime) {
		super();
		this.userId = userId;
		this.uuid = uuid;
		this.localDateTime = localDateTime;
	}

}
