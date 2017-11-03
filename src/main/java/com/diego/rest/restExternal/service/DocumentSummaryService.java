package com.diego.rest.restExternal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diego.rest.restExternal.dao.DocumentSummaryDAO;
import com.diego.rest.restExternal.model.DocumentSummary;

@Service
public class DocumentSummaryService {

	@Autowired
	private DocumentSummaryDAO documentSummaryDAO;

	public List<DocumentSummary> findAll() {
		return documentSummaryDAO.findAll();
	}

	public List<DocumentSummary> findByIdPatient(Long idPatient) {
		return documentSummaryDAO.findBySummarizedClinicalHistory_Patient_idPatient(idPatient);
	}

	public DocumentSummary findById(Long id) {
		return documentSummaryDAO.findOne(id);
	}

	public DocumentSummary saveDocument(DocumentSummary newDocumentSummary) {
		return documentSummaryDAO.save(newDocumentSummary);
	}

	public void delete(DocumentSummary documentSummary) {
		documentSummaryDAO.delete(documentSummary);
		
	}

	public void deleteAllDocuments() {
		documentSummaryDAO.deleteAll();
	}

	public DocumentSummary findByEhCOSIdentificationId(Long id) {
		return documentSummaryDAO.findByEhCOSIdentificationId(id);
	}
}
