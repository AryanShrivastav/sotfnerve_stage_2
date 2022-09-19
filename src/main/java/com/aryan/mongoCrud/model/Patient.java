package com.aryan.mongoCrud.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tutorials")
public class Patient {
	@Id
	private String id;
	
	private String patientname;
	private long patientcontactno;
	
	public Patient() {	
	}
	
	public Patient(String patientname,long patientcontactno) {
		this.patientname = patientname;
		this.patientcontactno = patientcontactno;
	}
	
	public String getid(){
		return id;
	}
	
	public String getpatientname() {
		return patientname;
	}
	
	public void setpatientname(String patientname) {
		this.patientname = patientname;
	}
	
	public long getpatientcontactno() {
		return patientcontactno;
	}
	
	public void setpatientcontactno(long patientcontactno) {
		this.patientcontactno = patientcontactno;
	}
	
	@Override
	public String toString() {
		return "Patient [ id=" + id + ", patientname=" + patientname +" , patientcontactno=" + patientcontactno + "]";
	}
}




















