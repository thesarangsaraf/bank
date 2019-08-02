package com.example.bank.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank.dto.CustomerDto;
import com.example.bank.dto.TransactionDto;
import com.example.bank.model.Customer;
import com.example.bank.model.Transaction;
import com.example.bank.repository.CustomerRepository;
import com.example.bank.repository.TransactionRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	public CustomerDto verify(CustomerDto customerDto) {
		Optional<Customer> optionalCustomer = customerRepository
				.findByCustomerIdAndCustomerPassword(customerDto.getCustomerId(), customerDto.getCustomerPassword());
		if (optionalCustomer.isPresent()) {
			return customerDto;
		}
		return null;
	}

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

	public String register(Customer customer) {
		customerRepository.save(customer);
		Optional<Customer> optionalCustomer = customerRepository
				.findByCustomerNameAndCustomerPassword(customer.getCustomerName(), customer.getCustomerPassword());
		if (!optionalCustomer.isPresent()) {
			return "Failed to create";
		}
		return optionalCustomer.get().toString();
	}

	public void depositLog(TransactionDto transactionDto) {
		Transaction transaction = new Transaction();
		transaction.setTransactionDate(new Date(System.currentTimeMillis()));
		transaction.setTransactionAmount(transactionDto.getTransactionAmount());
		transaction.setTransactionType(transactionDto.getTransactionType());

		Optional<Customer> optional = customerRepository.findById(transactionDto.getCustomerId());
		transaction.setCustomer(optional.get());
		transactionRepository.save(transaction);
	}
}
