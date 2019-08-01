package com.example.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.bank.repository.CustomerRepository;

public class CustomerController {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@PostMapping(value = "/withdraw")
	public String withDraw() {
		
		return null;
	}
}
