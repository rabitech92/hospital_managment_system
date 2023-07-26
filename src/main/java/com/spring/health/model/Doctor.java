package com.spring.health.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.spring.health.BaseClass.BaseClass;
import lombok.Data;

@Entity
@Data
@Table(name ="doctor")
public class Doctor extends BaseClass {
	

	
	@Column(length = 30)
	private String name;
	@Column(length = 120)
	private String email;
	@Column(length = 64)
	private String password;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "doctorPatient",
			joinColumns = {@JoinColumn(name = "doctor_id")},
			inverseJoinColumns = {@JoinColumn(name = "patient_id")}
			
			)
	
	private List<Patient> patient;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Patient> getPatient() {
		return patient;
	}

	public void setPatient(List<Patient> patient) {
		this.patient = patient;
	}
	
	


}
