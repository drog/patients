package com.diego.rest.restExternal.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.diego.rest.restExternal.dto.RestResponseDTO;
import com.diego.rest.restExternal.model.DocumentSummary;
import com.diego.rest.restExternal.model.Patient;
import com.diego.rest.restExternal.model.SummarizedClinicalHistory;
import com.diego.rest.restExternal.service.DocumentSummaryService;
import com.diego.rest.restExternal.service.PatientService;
import com.diego.rest.restExternal.service.SummarizedClinicalHistoryService;

@RestController
@RequestMapping("/document")
public class DocumentSummaryController {

	public static final Logger logger = LoggerFactory.getLogger(DocumentSummaryController.class);

	@Autowired
	private DocumentSummaryService documentSummaryService;
	
	@Autowired
	private SummarizedClinicalHistoryService summarizedClinicalHistoryService;
	
	@Autowired
	private PatientService patientService;
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public DocumentSummary findById(@PathVariable Long id) {
		logger.info("/"+id);
		return documentSummaryService.findById(id);
	}
	
	
	@RequestMapping(value = "/{id}/downloadPdf", method = RequestMethod.GET)
	public RestResponseDTO downloadPdf(@PathVariable Long id) {
		logger.info("/"+id);
		DocumentSummary documentSummary =  documentSummaryService.findByEhCOSIdentificationId(id);
		if( documentSummary != null && documentSummary.getFile() != null ){
			return new RestResponseDTO(documentSummary.getFile().getBase64());
		}else{
			return new RestResponseDTO(Boolean.FALSE, null, 0, null);
		}
	}
	
	
	@RequestMapping(value = "/documents/", method = RequestMethod.GET)
	public List<DocumentSummary>  findAll() {
		logger.info("/documents/");
		return documentSummaryService.findAll();
	}
	
	@RequestMapping(value = "/documents/{idPatient}", method = RequestMethod.GET)
	public List<DocumentSummary>  findAll(@PathVariable Long idPatient) {
		logger.info("/documents/"+ idPatient);
		return documentSummaryService.findByIdPatient(idPatient);
		
	}
	
	@RequestMapping(value = "/create/{idPatient}", method = RequestMethod.POST)
	public ResponseEntity<String> addDocument(@PathVariable("idPatient") long idPatient,@RequestBody DocumentSummary newDocumentSummary) {

		Patient patient = patientService.findById(idPatient);
		if( patient == null ){
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		
		SummarizedClinicalHistory summarizedClinicalHistory = summarizedClinicalHistoryService.findByIdPatient(idPatient);
		if( summarizedClinicalHistory == null ){
			summarizedClinicalHistory = new SummarizedClinicalHistory();
			summarizedClinicalHistory.setPatient(patient);
			summarizedClinicalHistoryService.save(summarizedClinicalHistory);
		}
		
		
		newDocumentSummary.setSummarizedClinicalHistory(summarizedClinicalHistory);
		DocumentSummary documentSummary = documentSummaryService.saveDocument(newDocumentSummary);
		if (documentSummary == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{id}").buildAndExpand(documentSummary.getIdDocumentSummary()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<DocumentSummary> updateDocument(@PathVariable("id") long id, @RequestBody DocumentSummary documentSummary) {
        logger.info("Updating DocumentSummary with id {}", id);
 
        DocumentSummary currentDocumentSummary = documentSummaryService.findById(id);
 
        if (currentDocumentSummary == null) {
        	logger.error("No fue posible actualizar. El DocumentSummary con el id {} no fue encontrado.", id);
        	return new ResponseEntity<DocumentSummary>(HttpStatus.NOT_FOUND);
        }
 
        currentDocumentSummary.setDocumentDate(documentSummary.getDocumentDate());
        currentDocumentSummary.setDescription(documentSummary.getDescription());
        currentDocumentSummary.setCode(documentSummary.getCode());
        currentDocumentSummary.setEhCOSIdentificationId(documentSummary.getEhCOSIdentificationId());
        
        documentSummaryService.saveDocument(currentDocumentSummary);
        return new ResponseEntity<DocumentSummary>(currentDocumentSummary, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<DocumentSummary> deleteDocument(@PathVariable("id") Long id) {
 
		DocumentSummary documentSummary = documentSummaryService.findById(id);
        if (documentSummary == null) {
            logger.error("No fue posible eliminar. El documentSummary con el id {} no fue encontrado.", id);
            return new ResponseEntity<DocumentSummary>(HttpStatus.NOT_FOUND);
        }
        documentSummaryService.delete(documentSummary);
        return new ResponseEntity<DocumentSummary>(HttpStatus.NO_CONTENT);
    }
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<DocumentSummary> deleteAllDocuments() {
        logger.info("Deleting All Users");
 
        documentSummaryService.deleteAllDocuments();
        return new ResponseEntity<DocumentSummary>(HttpStatus.NO_CONTENT);
    }
}
