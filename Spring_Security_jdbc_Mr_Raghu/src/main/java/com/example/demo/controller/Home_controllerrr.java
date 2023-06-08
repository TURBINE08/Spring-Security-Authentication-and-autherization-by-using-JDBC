package com.example.demo.controller;

//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home_controllerrr
{
	@GetMapping("/home")
	public String showhome() {
		return "HomePage";

	}
	
	@GetMapping("/welcome")
	public String showWelcome() {
		return "welcome to HomePage";
	}
	
	@GetMapping("/admin")
	public String showAdmin() {
		return "welcome to AdminPage";
	}
	
	@GetMapping("/emp")
	public String showEmployee() {
		return "welcome to EmployeePage";
	}
	@GetMapping("/std")
	public String showStd() {
		return "welcome to studentPage";
	}
	@GetMapping("/denied")
	public String showdnoed() {
		return "denide Page";
	}
}
