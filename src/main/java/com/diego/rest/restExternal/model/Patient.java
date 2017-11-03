package com.diego.rest.restExternal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "patient")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPatient;
	
	private String nip;
	
	private String name;
	
	private String birthDate;
	
	private String age;
	
	private String sex;
	
	@OneToOne(mappedBy="patient")
	@JsonIgnore
	private SummarizedClinicalHistory summarizedClinicalHistory;

	public Patient() {
		super();
	}

	public Patient(String nip, String name, String birthDate, String age, String sex) {
		super();
		this.nip = nip;
		this.name = name;
		this.birthDate = birthDate;
		this.age = age;
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "Patient [idPatient=" + idPatient + ", nip=" + nip + ", name=" + name + ", birthDate=" + birthDate + ", age=" + age
				+ ", sex=" + sex + "]";
	}

	public final Long getIdPatient() {
		return idPatient;
	}

	public final void setId(Long idPatient) {
		this.idPatient = idPatient;
	}

	public final String getNip() {
		return nip;
	}

	public final void setNip(String nip) {
		this.nip = nip;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final String getBirthDate() {
		return birthDate;
	}

	public final void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public final String getAge() {
		return age;
	}

	public final void setAge(String age) {
		this.age = age;
	}

	public final String getSex() {
		return sex;
	}

	public final void setSex(String sex) {
		this.sex = sex;
	}

	public SummarizedClinicalHistory getSummarizedClinicalHistory() {
		return summarizedClinicalHistory;
	}

	public void setSummarizedClinicalHistory(SummarizedClinicalHistory summarizedClinicalHistory) {
		this.summarizedClinicalHistory = summarizedClinicalHistory;
	}
	
	
	
}
