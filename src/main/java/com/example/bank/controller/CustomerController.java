package com.example.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.bank.dto.CustomerDto;
import com.example.bank.repository.CustomerRepository;
import com.example.bank.service.CustomerService;
import com.example.bank.service.LoginService;

@Controller
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	LoginService loginService;

	@Autowired
	CustomerService customerService;

	@PostMapping(value = "/withdraw")
	public String withDraw(CustomerDto customerDto) {
		customerService.withDraw(customerDto);
		return "successful";
	}

	@PostMapping(value = "/login")
	public String login(CustomerDto customerDto) {
		if (loginService.verify(customerDto) == null) {
			return "invalid";
		}
		return "home";
	}
}
