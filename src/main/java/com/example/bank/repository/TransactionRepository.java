package com.example.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bank.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
