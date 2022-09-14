package com.brillio.prescripty.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brillio.prescripty.entity.Doctor;
import com.brillio.prescripty.service.DoctorService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api")
public class DoctorController {
	
	//Defining Field for Doctor Service
	private DoctorService doctorService;

	//Dependency Injection
	public DoctorController(DoctorService doctorService) {
		super();
		this.doctorService = doctorService;
	}
	
	// Adding mapping for GET - /doctors - returns list of patients
	@GetMapping("/doctors")
	public List<Doctor> getAll(){
		return this.doctorService.getAll();
	}
	
	// Adding mapping for GET - /doctors/{id} - returns the details of patient for the provided id.
	@GetMapping("/doctors/{id}")
	public Doctor getSingleDoctor(@PathVariable int id) {
		Doctor currentDoctor = this.doctorService.getById(id);
		
		if(currentDoctor == null) throw new RuntimeException("Doctor for the id" + id + " not found.");
		
		return currentDoctor;
	}
	
	// Adding Mapping for POST - /doctors - adds a new patient.
	@PostMapping("/doctors")
		public Doctor addDoctor(@RequestBody Doctor currentDoctor) {
		// Setting id = 0, if there is an id in the request body.
		currentDoctor.setId(0);
		
		//Adding the Doctor
		this.doctorService.save(currentDoctor);
		
		return currentDoctor;
	}

	
	// Adding mapping for PUT - /doctors - updates the existing patient.
	@PutMapping("/doctors")
	public Doctor updatePatient(@RequestBody Doctor updateDoctor) {
		this.doctorService.save(updateDoctor);
		return updateDoctor;
	}
	
	// Adding mapping for DELETE - /doctors/{id} - deletes the patient corresponding to the given id.
	@DeleteMapping("/doctors/{id}")
	public String deleteDoctors(@PathVariable int id) {
		Doctor doctorToBeDeleted = this.doctorService.getById(id);
		
		// If the patient is not found then throwing exception.
		if(doctorToBeDeleted == null) throw new RuntimeException("Doctor having id " + id + " not found.");
		
		this.doctorService.deleteById(id);

		
		return "Doctor deleted successfully for id:" + id;
	}
	
	
}
