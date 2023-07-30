package com.spring.health.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="doctor")
public class Doctor extends BaseClass {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
	
	@Column(length = 30,nullable = false)
	private String name;
	@Column(length = 120,nullable = false)
	private String email;
	@Column(length = 64,nullable = false)
	private String password;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "doctorPatient",
			joinColumns = {@JoinColumn(name = "doctor_id")},
			inverseJoinColumns = {@JoinColumn(name = "patient_id")}
			)
	private List<Patient> patient;

}
