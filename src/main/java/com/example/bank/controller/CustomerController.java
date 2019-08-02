package com.example.bank.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@GetMapping(value = "/login")
	public String login() {
		return "login";
	}

	@ResponseBody
	@PostMapping(value = "/withdraw")
	public String withDraw(@Valid TransactionDto transactionDto) {
		customerService.transactionLog(transactionDto);
		return customerService.withDraw(transactionDto);
	}

	@ResponseBody
	@PostMapping(value = "/deposit")
	public String deposit(@RequestBody TransactionDto transactionDto) {
		customerService.transactionLog(transactionDto);
		return customerService.deposit(transactionDto);
	}

	@PostMapping(value = "/home")
	public String login(@Valid CustomerDto customerDto) {
		System.out.println(customerDto.toString());
		if (customerService.verify(customerDto) == null) {
			return "invalid";
		}
		return "home";
	}

	@PostMapping(value = "/register", produces = "text/html")
	public String register(@Valid Customer customer) {
		customerService.register(customer);
		return "successful";
	}
}
