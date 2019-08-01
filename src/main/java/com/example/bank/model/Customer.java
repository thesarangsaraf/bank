package com.example.bank.model;

public class Customer {
	Integer customerId;
	String customerName;
	String customerPassword;
	Integer customerBalance;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public Integer getCustomerBalance() {
		return customerBalance;
	}

	public void setCustomerBalance(Integer customerBalance) {
		this.customerBalance = customerBalance;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerPassword="
				+ customerPassword + ", customerBalance=" + customerBalance + "]";
	}
}
