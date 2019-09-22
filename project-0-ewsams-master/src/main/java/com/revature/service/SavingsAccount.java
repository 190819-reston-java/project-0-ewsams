package com.revature.service;

public class SavingsAccount extends Account{

	private static String accountType = "Savings";

	public String getAccountType() {
	return accountType;
	}

	public void setAccountType(String accountType) {
		SavingsAccount.accountType = accountType;
	}

	public SavingsAccount(double initialDepostit) {
		super();
		this.setBalance(initialDepostit);
		Account.numberOfAccounts++;
	}
	
	@Override
	public String toString() {
		return "Account [accountType=" + accountType + ", balance=" + getBalance() +
				", accountNumber=" + getAccountNumber() + "]";
		}
	 

}