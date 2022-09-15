package com.brillio.prescripty.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.brillio.prescripty.dao.AdminDAO;
import com.brillio.prescripty.entity.Admin;

@Service
public class AdminService {
	private AdminDAO adminDAO;
	
	public AdminService(AdminDAO adminDAO) {
		super();
		this.adminDAO = adminDAO;
	}
	
	@Transactional
	public List<Admin> getAll() {
		return this.adminDAO.getAll();
	}
	
	@Transactional
	public Admin getByEmail(String email) {
		return this.adminDAO.getByEmail(email);
	}
	
	@Transactional
	public void save(Admin currentAdmin) {
		this.adminDAO.save(currentAdmin);
	}

}
