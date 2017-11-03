package com.diego.rest.restExternal.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "summarized_clinical_history")
public class SummarizedClinicalHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long idSummarizedClinicalHistory;
	
	@OneToOne
	@JoinColumn(name = "id_patient")
	@JsonIgnore
	private Patient patient;
	
	@OneToMany(mappedBy="summarizedClinicalHistory", fetch = FetchType.EAGER)
	private List<DocumentSummary> documents;

	public final Long getIdSummarizedClinicalHistory() {
		return idSummarizedClinicalHistory;
	}

	public final void setIdSummarizedClinicalHistory(Long idSummarizedClinicalHistory) {
		this.idSummarizedClinicalHistory = idSummarizedClinicalHistory;
	}
	
	public final Patient getPatient() {
		return patient;
	}

	public final void setPatient(Patient patient) {
		this.patient = patient;
	}

	public final List<DocumentSummary> getDocuments() {
		return documents;
	}

	public final void setDocuments(List<DocumentSummary> documents) {
		this.documents = documents;
	}
	
}
