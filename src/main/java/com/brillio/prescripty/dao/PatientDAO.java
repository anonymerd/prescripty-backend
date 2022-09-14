package com.brillio.prescripty.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.brillio.prescripty.entity.Patient;

@Repository
public class PatientDAO {
	// Defining field for the Entity Manager.
	private EntityManager entityManager;
	
	// Injecting dependencies using Constructor DI.
	@Autowired
	public PatientDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public List<Patient> getAll() {
		// Getting the current hibernate session.
		Session currentSession = this.entityManager.unwrap(Session.class);
		
		// Creating the query to get all patients.
		Query sql = currentSession.createQuery("from Patient", Patient.class);
		
		// Executing the query and storing the results.
		List<Patient> patients = sql.getResultList();
		
		// Returning the results.
		return patients;
	}
	
	public Patient getById(int id) {
		// Getting the current hibernate session.
		Session currentSession = this.entityManager.unwrap(Session.class);
		
		// Getting the patient using the id provided.
		Patient currentPatient = currentSession.get(Patient.class, id);
		
		// Returning the patient.
		return currentPatient;
	}

	public void save(Patient currentPatient) {
		// Getting the current hibernate session.
		Session currentSession = this.entityManager.unwrap(Session.class);
		
		// Saving the provided patient.
		currentSession.saveOrUpdate(currentPatient);
	}
	
	public void deleteById(int id) {
		// Getting the current hibernate session
		Session currentSession = this.entityManager.unwrap(Session.class);
		
		// Deleting the object having the given primary key.
		Query sql = currentSession.createQuery("delete from Patient where id = :patientId");
		sql.setParameter("patientId", id);
		sql.executeUpdate();
	}

}
