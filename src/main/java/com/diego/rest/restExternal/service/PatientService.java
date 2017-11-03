package com.diego.rest.restExternal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diego.rest.restExternal.dao.PatientDAO;
import com.diego.rest.restExternal.model.Patient;

@Service
public class PatientService {
	
	@Autowired
	private PatientDAO patientDAO;

	public Patient findByNip(String nip) {
		return patientDAO.findByNip(nip);
	}


	public List<Patient> findAll() {
		return patientDAO.findAll();
	}

	public Patient save(Patient newPatient) {
		return patientDAO.save(newPatient);
	}

	public Patient findById(long id) {
		return patientDAO.findOne(id);
	}

	public void deleteByID(long id) {
		patientDAO.delete(id);
		
	}

	public void deleteAllPatients() {
		patientDAO.deleteAll();
	}
}
