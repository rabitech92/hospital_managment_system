package com.spring.health.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.spring.health.BaseClass.BaseClass;
import lombok.Data;
import org.hibernate.annotations.*;

@Entity
@Data
@Table(name="patient")
@SQLDelete(sql = "UPDATE patient SET deleted = true WHERE id=?")
@FilterDef(name = "deletedPatientFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedPatientFilter", condition = "deleted = :isDeleted")
public class Patient extends BaseClass {
	

	@Column(length = 30, nullable = false)
	private String name;
	
	@Column(length = 12,nullable = false)
	private int age ;
	
	@Column(unique = true, nullable = false,length =120 )
	private String nid;
	
	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	
	

}
