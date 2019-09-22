package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.revature.model.BankingCustomer;

public class BankingCustomerDoa {

	private static Logger logger = Logger.getLogger(BankingCustomerDoa.class.getName());
	private static final String SqlError = "Database Error";
	private static final String FIRSTNAME = "FIRST_NAME";
	private static final String LASTNAME = "LAST_NAME";
	private static final String EMAIL = "EMAIL";
	private static final String SSN = "SSN";
	private static final String PASSWORD = "CUSTOMER_PASSWORD";
	private static final String BALANCE = "CUSTOMER_BALANCE";
	private static final String ACCOUNT_TYPE = "ACCOUNT_TYPE";
	private static final String ACCOUNT_NUMBER = "ACCOUNT_NUMBER";
	public BankingCustomer customer = null;
		
	
	public BankingCustomer loginCustomer(String email, String password) {
		BankingCustomer bank_customer = null;
		String sQl = "SELECT * FROM BANKING_CUSTOMER WHERE EMAIL  = ? AND CUSTOMER_PASSWORD = ?; ";
			ResultSet resultset = null;
			try (Connection connection = ConnectionUtility.getConnection(); 
				PreparedStatement preparedstatement = connection.prepareStatement(sQl);) {
				preparedstatement.setString(1, email);
				preparedstatement.setString(2, password);
				
					if(preparedstatement.execute()) {
						resultset = preparedstatement.getResultSet();
						while(resultset.next()) {
					bank_customer = new BankingCustomer(
					resultset.getString(FIRSTNAME),
					resultset.getString(LASTNAME),
					resultset.getString(EMAIL),
					resultset.getString(SSN),
					resultset.getString(PASSWORD),
					resultset.getDouble(BALANCE),
					resultset.getString(ACCOUNT_TYPE),
					resultset.getInt(ACCOUNT_NUMBER));
				}
				StreamCloser.close(preparedstatement);
				StreamCloser.close(resultset);
					}
			}catch (SQLException e) {
				logger.log(Level.INFO, SqlError, e);
			}
			return bank_customer;
		}

	public int createCustomer(BankingCustomer customer) {
		String sQL = "INSERT INTO BANKING_CUSTOMER (FIRST_NAME, LAST_NAME, EMAIL, SSN,"
				+ "CUSTOMER_PASSWORD,BALANCE,ACCOUNT_TYPE,ACCOUNT_NUMBER) VALUES (?, ?, ?, ?, ? , ? , ?, ?) ";
		int userCreated = 0;
		
		try (Connection connection = ConnectionUtility.getConnection(); 
			PreparedStatement preparedStatement = connection.prepareStatement(sQL);) {
			preparedStatement.setString(1, customer.getFirstName());
			preparedStatement.setString(2, customer.getLastName());
			preparedStatement.setString(3, customer.getEmail());
			preparedStatement.setString(4, customer.getsSN());
			preparedStatement.setString(5, customer.getPassword());
			preparedStatement.setDouble(6, customer.getAccount().getBalance());
			preparedStatement.setString(7, customer.getAccount().getAccountType());
			preparedStatement.setInt(8, customer.getAccount().getAccountNumber());
			userCreated = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			logger.log(Level.INFO, SqlError, e);
			System.out.println("Banking Information not found....");

		}
		return userCreated;
	}

	public void updateAccount(BankingCustomer customer) {
		String sql = "UPDATE BANKING_CUSTOMER " + "SET BALANCE = ?" + " WHERE SSN = ?";
		try (Connection connection = ConnectionUtility.getConnection(); 
			PreparedStatement preparedstatement = connection.prepareStatement(sql);) {
			preparedstatement.setDouble(1, customer.getAccount().getBalance());
			preparedstatement.setString(2, customer.getsSN());
			preparedstatement.executeUpdate();
		} catch (SQLException e) {
			logger.error( SqlError, e);
		}
	}
}
	