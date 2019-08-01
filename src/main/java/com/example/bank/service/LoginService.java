package com.example.bank.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank.dto.CustomerDto;
import com.example.bank.model.Customer;
import com.example.bank.repository.CustomerRepository;

@Service
public class LoginService {

	@Autowired
	private CustomerRepository customerRepository;

	public CustomerDto verify(CustomerDto customerDto) {
		Optional<Customer> optionalCustomer = customerRepository
				.findByCustomerIdAndCustomerPassword(customerDto.getCustomerId(), customerDto.getCustomerPassword());
		if (optionalCustomer.isPresent()) {
			return customerDto;
		}
		return null;
	}
}
