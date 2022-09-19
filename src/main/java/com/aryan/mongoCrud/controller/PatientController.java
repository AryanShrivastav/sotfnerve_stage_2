package com.aryan.mongoCrud.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aryan.mongoCrud.model.Patient;
import com.aryan.mongoCrud.repository.PatientRepository;

@RestController
@RequestMapping("/api")
public class PatientController {
	
	@Autowired
	PatientRepository patientRepository;
	
	@GetMapping("/tutorials")
	public ResponseEntity <List<Patient>> getAllPatients(@RequestParam(required = false)String patientname){
		try {
			List<Patient> tutorials = new ArrayList<Patient>();
			
			if (patientname == null) 
				patientRepository.findAll().forEach(tutorials::add);
			else
				patientRepository.findBypatientnameContaining(patientname).forEach(tutorials::add);
			
			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		}
		catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/tutorials/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable("id") String id) {
	    Optional<Patient> patientData = patientRepository.findById(id);

	    if (patientData.isPresent()) {
	      return new ResponseEntity<>(patientData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
	@PostMapping("/tutorials")
	  public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
	    try {
	      Patient _patient = patientRepository.save(new Patient(patient.getpatientname(), patient.getpatientcontactno()));
	      return new ResponseEntity<>(_patient, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	@PutMapping("/tutorials/{id}")
	  public ResponseEntity<Patient> updatePatient(@PathVariable("id") String id, @RequestBody Patient patient) {
	    Optional<Patient> patientData = patientRepository.findById(id);

	    if (patientData.isPresent()) {
	      Patient _patient = patientData.get();
	      _patient.setpatientname(patient.getpatientname());
	      _patient.setpatientcontactno(patient.getpatientcontactno());
	      return new ResponseEntity<>(patientRepository.save(_patient), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
	@DeleteMapping("/tutorials/{id}")
	  public ResponseEntity<HttpStatus> deletePatient(@PathVariable("id") String id) {
	    try {
	      patientRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	 @DeleteMapping("/tutorials")
	  public ResponseEntity<HttpStatus> deleteAllPatients() {
	    try {
	      patientRepository.deleteAll();
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
}






















