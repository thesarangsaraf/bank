package com.example.bank.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.bank.dto.TransactionDto;
import com.example.bank.model.Customer;
import com.example.bank.model.Transaction;
import com.example.bank.repository.CustomerRepository;
import com.example.bank.repository.TransactionRepository;

public class TransactionController {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	TransactionRepository transactionRepository;

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
