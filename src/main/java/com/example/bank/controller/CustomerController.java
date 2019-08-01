package com.example.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bank.dto.CustomerDto;
import com.example.bank.dto.TransactionDto;
import com.example.bank.model.Customer;
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
	public String withDraw(TransactionDto transactionDto) {
		return customerService.withDraw(transactionDto);
	}

	@PostMapping(value = "/deposit")
	public String deposit(TransactionDto transactionDto) {
		return customerService.deposit(transactionDto);
	}

	@PostMapping(value = "/login")
	public String login(CustomerDto customerDto) {
		if (customerService.verify(customerDto) == null) {
			return "invalid";
		}
		return "home";
	}

	@PostMapping(value = "/register")
	public String register(Customer customer) {
		return customerService.register(customer);
	}
}
