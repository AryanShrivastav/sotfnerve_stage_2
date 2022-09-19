package com.aryan.mongoCrud.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.aryan.mongoCrud.model.Patient;

public interface PatientRepository extends MongoRepository<Patient,String>{
	  List<Patient> findBypatientnameContaining(String patientname);
	  List<Patient> findBypatientcontactnoContaining(long patientcontactno);
	}

