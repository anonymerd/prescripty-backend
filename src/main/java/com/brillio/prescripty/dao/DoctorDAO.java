package com.brillio.prescripty.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.brillio.prescripty.entity.Doctor;

@Repository
public class DoctorDAO {
	
	// Defining field for the Entity Manager.
	private EntityManager entityManager;
	
	// Injecting dependencies using Constructor DI.

	@Autowired
	public DoctorDAO(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}
	
	public List<Doctor> getAll(){
		// Getting the current hibernate session.
		Session currentSession = this.entityManager.unwrap(Session.class);
	
		// Creating the query to get all doctors.
		Query sql = currentSession.createQuery("From Doctor", Doctor.class);
		
		// Executing the query and storing the results.
		List<Doctor> doctors = sql.getResultList();
		
		// Returning the results.
		return doctors;
	}
	
	public Doctor getById(int id) {
		// Getting the current hibernate session.
		Session currentSession = this.entityManager.unwrap(Session.class);
		
		// Getting the doctor using the id provided.
		Doctor currentDoctor = currentSession.get(Doctor.class, id);
	
		//Returning the doctor
		return currentDoctor;
	}
	
	public void save(Doctor currentDoctor) {
		
		// Getting the current hibernate session
		Session currentSession = this.entityManager.unwrap(Session.class);
		
		//Saving the provided doctor
		currentSession.saveOrUpdate(currentDoctor);
		
	}
	
	public void deleteById(int id) {

		// Getting the current hibernate session
		Session currentSession = this.entityManager.unwrap(Session.class);
		
		// Deleting the object having the given primary key.
		Query sql = currentSession.createQuery("delete from Doctor where id = :doctorId");
		sql.setParameter("doctorId", id);
		sql.executeUpdate();
	}
	

}
