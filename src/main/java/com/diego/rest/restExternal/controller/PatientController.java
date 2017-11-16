package com.diego.rest.restExternal.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.diego.rest.restExternal.dto.RestResponseDTO;
import com.diego.rest.restExternal.model.Patient;
import com.diego.rest.restExternal.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {
	
	public static final Logger logger = LoggerFactory.getLogger(PatientController.class);

	@Autowired
	private PatientService patientService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Patient findById(@PathVariable String id) {
		logger.debug("/patient/"+id);
		Long idLong =  NumberUtils.parseNumber(id, Long.class); 
		
		return patientService.findById(idLong);
	}

	
	@RequestMapping(value = "/findByNip", params= {"nip", "ticket", "sessionId"}, method = RequestMethod.GET)
	public RestResponseDTO findByNip(@RequestParam String nip) {
		logger.debug("/patient/findByNip/"+nip);
		
		
		return new RestResponseDTO(patientService.findByNip(nip));
	}
	
	@RequestMapping(value = "/patients", method = RequestMethod.GET)
	public List<Patient>  findAll() {
		logger.debug("/patient/patients");
		return patientService.findAll();
	}
	
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<String> addPatient(@RequestBody Patient newPatient) {

		Patient patient = patientService.save(newPatient);
		if (patient == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{id}").buildAndExpand(patient.getIdPatient()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Patient> updateUser(@PathVariable("id") Long id, @RequestBody Patient patient) {
        logger.debug("Updating User with id {}", id);
 
        Patient currenPatient = patientService.findById(id);
 
        if (currenPatient == null) {
        	logger.error("No fue posible actualizar. El paciente con el id {} no fue encontrado.", id);
        	return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
        }
 
        currenPatient.setName(patient.getName());
        currenPatient.setAge(patient.getAge());
        currenPatient.setNip(patient.getNip());
        currenPatient.setBirthDate(patient.getBirthDate());
        currenPatient.setSex(patient.getSex());
 
        patientService.save(currenPatient);
        return new ResponseEntity<Patient>(currenPatient, HttpStatus.OK);
    }
	
	
	
	@RequestMapping(value = "/delete/{idPatient}", method = RequestMethod.DELETE)
    public ResponseEntity<Patient> deletePatient(@PathVariable("idPatient") Long id) {
 
        Patient patient = patientService.findById(id);
        if (patient == null) {
            logger.error("No fue posible eliminar. El paciente con el id {} no fue encontrado.", id);
            return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
        }
        patientService.deleteByID(id);
        return new ResponseEntity<Patient>(HttpStatus.NO_CONTENT);
    }
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Patient> deleteAllPatients() {
        logger.debug("Deleting All Users");
 
        patientService.deleteAllPatients();
        return new ResponseEntity<Patient>(HttpStatus.NO_CONTENT);
    }
	
}
