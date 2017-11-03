package com.diego.rest.restExternal.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "document_summary")
public class DocumentSummary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonInclude(Include.NON_NULL)
	private Long idDocumentSummary;
	
	private Long documentDate;
	
	private String description;
	
	private String code;
	
	private String docImage;
	
	@Column(name="ehcos_identification_id")
	private Long ehCOSIdentificationId;
	
	@JsonInclude(Include.NON_NULL)
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_file")
	private File file;
	
	
	@ManyToOne
	@JoinColumn(name = "id_summarized_clinical_history")
	@JsonIgnore
	private SummarizedClinicalHistory summarizedClinicalHistory;

	public final Long getIdDocumentSummary() {
		return idDocumentSummary;
	}

	public final void setIdDocumentSummary(Long idDocumentSummary) {
		this.idDocumentSummary = idDocumentSummary;
	}

	public final Long getDocumentDate() {
		return documentDate;
	}

	public final void setDocumentDate(Long documentDate) {
		this.documentDate = documentDate;
	}

	public final String getDescription() {
		return description;
	}

	public final void setDescription(String description) {
		this.description = description;
	}

	public final String getCode() {
		return code;
	}

	public final void setCode(String code) {
		this.code = code;
	}

	public final String getDocImage() {
		return docImage;
	}

	public final void setDocImage(String docImage) {
		this.docImage = docImage;
	}

	public final Long getEhCOSIdentificationId() {
		return ehCOSIdentificationId;
	}

	public final void setEhCOSIdentificationId(Long ehCOSIdentificationId) {
		this.ehCOSIdentificationId = ehCOSIdentificationId;
	}

	public final SummarizedClinicalHistory getSummarizedClinicalHistory() {
		return summarizedClinicalHistory;
	}

	public final void setSummarizedClinicalHistory(SummarizedClinicalHistory summarizedClinicalHistory) {
		this.summarizedClinicalHistory = summarizedClinicalHistory;
	}

	public final File getFile() {
		return file;
	}

	public final void setFile(File file) {
		this.file = file;
	}
	
}
