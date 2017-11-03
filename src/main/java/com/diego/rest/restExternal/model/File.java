package com.diego.rest.restExternal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "file")
public class File {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonInclude(Include.NON_NULL)
	private Long idFile;
	
	private Integer fileSize;
	
	private String fileType;
	
	private String fileName;
	
	private String base64;
	
	@OneToOne(mappedBy = "file")
	@JsonIgnore
    private DocumentSummary documentSummary;

	public final Long getIdFile() {
		return idFile;
	}

	public final void setIdFile(Long idFile) {
		this.idFile = idFile;
	}

	public final Integer getFileSize() {
		return fileSize;
	}

	public final void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	public final String getFileType() {
		return fileType;
	}

	public final void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public final String getFileName() {
		return fileName;
	}

	public final void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public final String getBase64() {
		return base64;
	}

	public final void setBase64(String base64) {
		this.base64 = base64;
	}

	public final DocumentSummary getDocumentSummary() {
		return documentSummary;
	}

	public final void setDocumentSummary(DocumentSummary documentSummary) {
		this.documentSummary = documentSummary;
	}
}
