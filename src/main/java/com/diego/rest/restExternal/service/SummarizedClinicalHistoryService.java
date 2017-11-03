package com.diego.rest.restExternal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diego.rest.restExternal.dao.SummarizedClinicalHistoryDAO;
import com.diego.rest.restExternal.model.SummarizedClinicalHistory;

@Service
public class SummarizedClinicalHistoryService {
	
	@Autowired
	private SummarizedClinicalHistoryDAO summarizedClinicalHistoryDAO;

	public SummarizedClinicalHistory findByIdPatient(Long idPatient) {
		return summarizedClinicalHistoryDAO.findByPatient_idPatient(idPatient);
	}
	
	public SummarizedClinicalHistory save(SummarizedClinicalHistory newSummarizedClinicalHistory) {
		return summarizedClinicalHistoryDAO.save(newSummarizedClinicalHistory);
	}

}
