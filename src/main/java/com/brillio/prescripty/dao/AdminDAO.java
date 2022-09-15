package com.brillio.prescripty.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.brillio.prescripty.entity.Admin;

@Repository
public class AdminDAO {
	private EntityManager entityManager;

	@Autowired
	public AdminDAO(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}
	
	public List<Admin> getAll(){
		// Getting the current hibernate session.
		Session currentSession = this.entityManager.unwrap(Session.class);
	
		// Creating the query to get all admins.
		Query sql = currentSession.createQuery("From Admin", Admin.class);
		
		// Executing the query and storing the results.
		List<Admin> admins = sql.getResultList();
		
		// Returning the results.
		return admins;
	}
	
	public Admin getByEmail(String email) {
		// Getting the current hibernate session.
		Session currentSession = this.entityManager.unwrap(Session.class);
		
		Query sql = currentSession.createQuery("from Admin where email = :email", Admin.class);
		sql.setParameter("email", email);
		
		Admin admin = (Admin)sql.getSingleResult();
		
		//Returning the admin
		return admin;
	}
	
	public void save(Admin currentAdmin) {
		
		// Getting the current hibernate session
		Session currentSession = this.entityManager.unwrap(Session.class);
		
		//Saving the provided doctor
		currentSession.save(currentAdmin);
		
	}
	

}
