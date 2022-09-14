package com.brillio.prescripty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brillio.prescripty.dao.PatientDAO;
import com.brillio.prescripty.entity.Patient;

@Service
public class PatientService {
	
	// Defining field for Patient DAO.
	private PatientDAO patientDAO;
	
	// Injecting dependencies using constructor DI.
	@Autowired
	public PatientService(PatientDAO patientDAO) {
		this.patientDAO = patientDAO;
	}
	
	// Defining the service methods.
	@Transactional
	public List<Patient> getAll() {
		return this.patientDAO.getAll();
	}
	
	@Transactional
	public Patient getById(int id) {
		return this.patientDAO.getById(id);
	}
	
	@Transactional
	public void save(Patient currentPatient) {
		this.patientDAO.save(currentPatient);
	}
	
	@Transactional
	public void deleteById(int id) {
		this.patientDAO.deleteById(id);
	}
	
	

}
