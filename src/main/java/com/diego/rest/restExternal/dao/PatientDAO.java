package com.diego.rest.restExternal.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.diego.rest.restExternal.model.Patient;

public interface PatientDAO extends CrudRepository<Patient, Long> {

	   Patient findByNip(String nip);
	   
	   List<Patient> findAll();
	}
