package com.revature.service;

public class CheckingAccount extends Account {

	private static String accountType = "CHECKING";

	public CheckingAccount(double initialDeposit) {
		super();
		this.setBalance(initialDeposit);
		Account.numberOfAccounts++;

	}

	@Override
	public String toString() {
		return "Account [accountType=" + accountType + ", balance=" + getBalance() +
				", accountNumber=" + getAccountNumber() + "]";
		}
	}
