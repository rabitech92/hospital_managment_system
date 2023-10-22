package com.spring.health.model;

import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document("currentSession")
@ToString
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



	public ObjectId getUserId() {
		return userId;
	}

	public void setUserId(ObjectId userId) {
		this.userId = userId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}



	public String getUserType() {
		return userType;
	}



	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	

}
