package com.example.bank.service;

import org.springframework.stereotype.Service;

import com.example.bank.dto.CustomerDto;

@Service
public class CustomerService {
	public CustomerDto withDraw(CustomerDto customerDto) {
		return customerDto;
	}
}
