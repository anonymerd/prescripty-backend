package com.brillio.prescripty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brillio.prescripty.entity.Patient;
import com.brillio.prescripty.service.PatientService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api")
public class PatientController {
	// Defining field for Patient Service.
	private PatientService patientService;
	
	// Injecting dependencies using Constructor DI.
	@Autowired
	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}
	
	// Adding mapping for GET - /patients - returns list of patients
	@GetMapping("/patients")
	public List<Patient> getAll() {
		return patientService.getAll();
	}
	
	// Adding mapping for GET - /patients/{id} - returns the details of patient for the provided id.
	@GetMapping("/patients/{id}")
	public Patient getSinglePatient(@PathVariable int id) {
		Patient currentPatient = this.patientService.getById(id);
		
		if(currentPatient == null) throw new RuntimeException("Patient for the id " + id + " not found.");
		
		return currentPatient;
	}
	
	// Adding Mapping for POST - /patient - adds a new patient.
	@PostMapping("/patients")
	public Patient addPatient(@RequestBody Patient currentPatient) {
		// Setting id = 0, if there is an id in the request body.
		currentPatient.setId(0);
		
		// Adding the patient.
		this.patientService.save(currentPatient);
		
		return currentPatient;
	}
	
	// Adding mapping for PUT - /patients - updates the existing patient.
	@PutMapping("/patients")
	public Patient updatePatient(@RequestBody Patient updatedPatient) {
		this.patientService.save(updatedPatient);
		
		return updatedPatient;
	}
	
	// Adding mapping for DELETE - /patients/{id} - deletes the patient corresponding to the given id.
	@DeleteMapping("/patients/{id}")
	public String deletePatient(@PathVariable int id) {
		Patient patientToBeDeleted = this.patientService.getById(id);
		
		// If the patient is not found then throwing exception.
		if(patientToBeDeleted == null) throw new RuntimeException("Patient having id " + id + " not found.");
		
		this.patientService.deleteById(id);
		
		return "Patient deleted sucessfully for id " + id;
	}
}
