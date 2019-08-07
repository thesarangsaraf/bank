package com.example.bank.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

	
	@PostMapping(value = "/withdraw")
	public String withDraw(@Valid TransactionDto transactionDto) {
		customerService.transactionLog(transactionDto);
		customerService.withDraw(transactionDto);
		return "successful";
	}

	@GetMapping(value = "/newregister")
	public String newRegister() {
		return "register";
	}
	
	@PostMapping(value = "/deposit")
	public String deposit(@Valid TransactionDto transactionDto) {
		customerService.transactionLog(transactionDto);
		customerService.deposit(transactionDto);
		return "successful";
	}

	@PostMapping(value = "/home")
	public String login(@Valid CustomerDto customerDto, Model model) {
		System.out.println(customerDto.toString());
		if (customerService.verify(customerDto) == null) {
			return "invalid";
		}
		Optional<Customer> optionalCust = customerRepository.findById(customerDto.getCustomerId());
		List<Customer> custList = new ArrayList<Customer>();
		custList.add(optionalCust.get());
		
		model.addAttribute("custList", custList);

		return "try";
	}

	@PostMapping(value = "/register", produces = "text/html")
	public String register(@Valid Customer customer) {
		customerService.register(customer);
		return "successful";
	}
	
	@GetMapping(value = "/hm")
	public String goToHome() {
		return "home";
	}
	
	
	
}
