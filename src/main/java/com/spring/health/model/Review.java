package com.spring.health.model;



import lombok.*;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;


@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Review {
	
	@Id
	private Integer reviewId;
	private Patient patient;
	private Doctor doctor;
	private Appointment appointment;
	private String reviewContent;
	private float rating;

	@Override
	public int hashCode() {
		return Objects.hash(reviewId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		return Objects.equals(reviewId, other.reviewId);
	}
	
	

}






