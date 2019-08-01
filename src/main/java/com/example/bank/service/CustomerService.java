package com.example.bank.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank.dto.TransactionDto;
import com.example.bank.model.Customer;
import com.example.bank.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	public String withDraw(TransactionDto transactionDto) {
		Optional<Customer> optionalCustomer = customerRepository.findById(transactionDto.getCustomerId());
		if (!optionalCustomer.isPresent()) {
			return "invalidId";
		}
		Customer customer = optionalCustomer.get();
		if (customer.getCustomerBalance() >= transactionDto.getTransactionAmount()) {
			customer.setCustomerBalance(customer.getCustomerBalance() - transactionDto.getTransactionAmount());
			customerRepository.save(customer);
			return "success";
		}
		return "insufficient balance";
	}

	public String deposit(TransactionDto transactionDto) {
		Optional<Customer> optionalCustomer = customerRepository.findById(transactionDto.getCustomerId());
		if (!optionalCustomer.isPresent()) {
			return "invalidId";
		}
		Customer customer = optionalCustomer.get();
		customer.setCustomerBalance(customer.getCustomerBalance() + transactionDto.getTransactionAmount());
		customerRepository.save(customer);
		return "success";
	}
}
