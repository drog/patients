package com.diego.rest.restExternal.dao;

import org.springframework.data.repository.CrudRepository;

import com.diego.rest.restExternal.model.SummarizedClinicalHistory;

public interface SummarizedClinicalHistoryDAO extends CrudRepository<SummarizedClinicalHistory, Long>{

	SummarizedClinicalHistory findByPatient_idPatient(Long idPatient);
}
