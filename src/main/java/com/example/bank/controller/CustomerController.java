package com.example.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bank.dto.CustomerDto;
import com.example.bank.dto.TransactionDto;
import com.example.bank.repository.CustomerRepository;
import com.example.bank.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CustomerService customerService;

	@ResponseBody
	@PostMapping(value = "/withdraw")
	public String withDraw(@RequestBody TransactionDto transactionDto) {
		return customerService.withDraw(transactionDto);
	}

	@ResponseBody
	@PostMapping(value = "/deposit")
	public String deposit(@RequestBody TransactionDto transactionDto) {
		customerService.depositLog(transactionDto);
		return customerService.deposit(transactionDto);
	}

	@PostMapping(value = "/login")
	public String login(@RequestBody CustomerDto customerDto) {
		if (customerService.verify(customerDto) == null) {
			return "invalid";
		}
		return "home";
	}

	@GetMapping(value = "/register", produces = "text/html")
	public String register() {
		return "register";
	}
}
