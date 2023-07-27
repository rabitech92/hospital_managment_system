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

//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}

//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	public List<Patient> getPatient() {
//		return patient;
//	}
//	public void setPatient(List<Patient> patient) {
//		this.patient = patient;
//	}
//
//


}
