package com.diego.rest.restExternal.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.diego.rest.restExternal.model.DocumentSummary;

public interface DocumentSummaryDAO extends CrudRepository<DocumentSummary, Long> {

	List<DocumentSummary> findAll();

	List<DocumentSummary> findBySummarizedClinicalHistory_Patient_idPatient(Long idPatient);

	DocumentSummary findByEhCOSIdentificationId(Long id);
}
