package com.revature.model;

import com.revature.service.Account;

public class BankingCustomer {
	private  String firstName;
	private  String lastName;
	private  String email;
	private  String sSN;
	private  String password;
	private  Account account;
	private double balance;
	private String accountType;
	private int accountNumber;

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setsSN(String sSN) {
		this.sSN = sSN;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getsSN() {
		return sSN;
	}

	public String getPassword() {
		return password;
	}


	public Account getAccount() {
		return account;
	}

	public BankingCustomer(String firstName, String lastName, String email, String sSN, String password, Account account) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.sSN = sSN;
		this.password = password;
		this.account = account;
	}

	public BankingCustomer(String firstName, String lastName, String email, String sSN, String password,
			double balance, String accountType, int accountNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.sSN = sSN;
		this.password = password;
		this.balance = balance;
		this.accountType = accountType;
		this.accountNumber = accountNumber;
	}

	@Override
	public String toString() {
		return "Customer [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", sSN=" + sSN
				+ ", password=" + password + ", account=" + account + "]";
	}
}

