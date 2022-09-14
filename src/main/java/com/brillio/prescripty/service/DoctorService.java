package com.brillio.prescripty.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brillio.prescripty.dao.DoctorDAO;
import com.brillio.prescripty.entity.Doctor;

@Service
public class DoctorService {
	//Defining Fields for Doctor DAO
	private DoctorDAO doctorDAO;

	//Dependency Injection 
	@Autowired
	public DoctorService(DoctorDAO doctorDAO) {
		super();
		this.doctorDAO = doctorDAO;
	}
	
	//Defining the Service methods
	@Transactional
	public List<Doctor> getAll(){
		return this.doctorDAO.getAll();
	}
	
	@Transactional
	public Doctor getById(int id) {
		return this.doctorDAO.getById(id);
	}
	
	@Transactional
	public void save(Doctor currentDcotor) {
		this.doctorDAO.save(currentDcotor);
	}
	
	@Transactional
	public void deleteById(int id) {
		this.doctorDAO.deleteById(id);
	}
	
	
	
}
