package com.revature.exception;

@SuppressWarnings("serial")
public class InsufficientFunds extends Exception{

	public InsufficientFunds(String msg) {
		super(msg);
	}
}
