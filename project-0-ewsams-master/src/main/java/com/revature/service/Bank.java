package com.revature.service;

import java.util.ArrayList;

import com.revature.model.BankingCustomer;

public class Bank {

	public static ArrayList<BankingCustomer> customerList= new ArrayList<BankingCustomer>();
	
	public static void addCustomer(BankingCustomer customer) {
		customerList.add(customer);
	}	
	
	public static ArrayList<BankingCustomer> getBankingCustomersList(){
		return Bank.customerList;
	}
}
	
