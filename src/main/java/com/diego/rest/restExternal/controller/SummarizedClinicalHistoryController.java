package com.diego.rest.restExternal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.diego.rest.restExternal.dto.RestResponseDTO;
import com.diego.rest.restExternal.model.DocumentSummary;
import com.diego.rest.restExternal.model.SummarizedClinicalHistory;
import com.diego.rest.restExternal.service.SummarizedClinicalHistoryService;

@RestController
@RequestMapping("/history")
public class SummarizedClinicalHistoryController {
	
	public static final Logger logger = LoggerFactory.getLogger(SummarizedClinicalHistoryController.class);
	
	@Autowired
	private SummarizedClinicalHistoryService summarizedClinicalHistoryService;
	
	@RequestMapping(value = "/{id}/summarizedClinicalHistory", method = RequestMethod.GET)
	public RestResponseDTO findByIdPatient(@PathVariable Long id) {
		logger.debug("/history/"+id+"/summarizedClinicalHistory");
		
		SummarizedClinicalHistory summarizedClinicalHistory = summarizedClinicalHistoryService.findByIdPatient(id);
		if( summarizedClinicalHistory != null && !CollectionUtils.isEmpty(summarizedClinicalHistory.getDocuments()) ){
			for (DocumentSummary documentSummary : summarizedClinicalHistory.getDocuments()) {
				documentSummary.setIdDocumentSummary(null);
				documentSummary.setFile(null);
			}
		}
		
		return new RestResponseDTO(summarizedClinicalHistory);
	}

}
