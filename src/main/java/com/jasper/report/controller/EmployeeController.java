package com.jasper.report.controller;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jasper.report.entity.Employee;
import com.jasper.report.repository.EmployeeRepository;
import com.jasper.report.service.EmployeeService;


import net.sf.jasperreports.engine.JRException;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/addEmployees")
	public List<Employee> addProducts(@RequestBody List<Employee> employee){
		return employeeRepository.saveAll(employee);
	}
	
	@GetMapping("/getEmployees")
	public List<Employee> getEmployees(){
		return employeeRepository.findAll();
	}
	
	@GetMapping("/report/{format}")
	public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
		return employeeService.exportReport(format);
	}
	
	@GetMapping("/getEmployeeById/{id}")
	public Employee findById(@PathVariable int id) {
		return employeeRepository.findById(id).orElse(null);
	}
}
