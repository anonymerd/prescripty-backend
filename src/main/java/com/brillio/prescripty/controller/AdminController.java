package com.brillio.prescripty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brillio.prescripty.entity.Admin;
import com.brillio.prescripty.login.Login;
import com.brillio.prescripty.service.AdminService;
import com.brillio.prescripty.utils.APIResponse;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api")
public class AdminController {
	private AdminService adminService;
	
	
	
	@Autowired
	public AdminController(AdminService adminService) {
		super();
		this.adminService = adminService;
	}

	@PostMapping("/signup")
	public Admin addAdmin(@RequestBody Admin currentAdmin) {
		currentAdmin.setId(0);
		
		this.adminService.save(currentAdmin);
		
		return currentAdmin;
	}
	
	@PostMapping("/login")
	public APIResponse login(@RequestBody Login login) {
		System.out.println(login.getPassword());
		Admin currentAdmin = this.adminService.getByEmail(login.getEmail());
		
		System.out.println(currentAdmin.getPassword());
		
		if(currentAdmin == null) throw new RuntimeException("User does not exists");
		else if(!currentAdmin.getPassword().equals( login.getPassword())) throw new RuntimeException("Passwords mismatch");
		else return new APIResponse(200, "Login Success", currentAdmin);
	}
	
}
