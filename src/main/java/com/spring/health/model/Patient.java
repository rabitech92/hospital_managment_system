package com.spring.health.model;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.spring.health.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="patient")
public class Patient {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 30, nullable = false)
	private String name;

	@Column(length = 12,nullable = false)
	private int age ;
	
	@Column(unique = true, nullable = false,length =120 )
	private String nid;

	@Enumerated(EnumType.STRING)
	private Status status;
	
	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;

}
