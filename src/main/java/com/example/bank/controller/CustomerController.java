package com.example.bank.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

	TransactionController transactionController = new TransactionController();

	@ResponseBody
	@PostMapping(value = "/withdraw")
	public String withDraw(@RequestBody TransactionDto transactionDto) {
		return customerService.withDraw(transactionDto);
	}

	@ResponseBody
	@PostMapping(value = "/deposit")
	public String deposit(@RequestBody TransactionDto transactionDto) {
		transactionController.depositLog(transactionDto);
		return customerService.deposit(transactionDto);
	}

	@PostMapping(value = "/login")
	public String login(@RequestBody CustomerDto customerDto) {
		if (customerService.verify(customerDto) == null) {
			return "invalid";
		}
		return "home";
	}

	@ResponseBody
	@PostMapping(value = "/register")
	public String register(@Valid Customer customer) {
		System.out.println("From controller " + customer.toString());
		return customerService.register(customer);
	}
}
