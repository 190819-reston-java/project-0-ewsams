package JunitTest;


import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.revature.model.BankingCustomer;
import com.revature.repository.BankingCustomerDoa;
import com.revature.service.Account;


public class EvaluationServiceTest {

	BankingCustomerDoa evaluationUserDao = new BankingCustomerDoa();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();


	@Test
	public void testForSuccessfullLogin() {
		Account account = new Account();
		BankingCustomer customer = new BankingCustomer("just", "meadors", "meadors@yahoo.com", "333222444", "justinm" ,account);
		evaluationUserDao.loginCustomer("meadors@yahoo.com","justinm");
		String firstName = customer.getFirstName();
		assertEquals("Mr", firstName);
	}
	
	@Test(expected=NullPointerException.class)
	public void testLoginFailed() throws IOException, SQLException {
		Account account = new Account();
		BankingCustomer customer = new BankingCustomer("just", "meadors", "meadors@yahoo.com", "333222444", "justinm" ,account);
		evaluationUserDao.loginCustomer("meadors@yahoo.com","justinm");
       fail(customer.getFirstName());
	}
	
	@Test
	public void testForEmailSuccess() {
		Account account = new Account();
		BankingCustomer customer = new BankingCustomer("just", "meadors", "meadors@yahoo.com", "333222444", "justinm" ,account);
		String email = customer.getEmail();
		assertEquals(email,email);
	}
	
	@Test
	public void testForSsnSuccess() {
		BankingCustomer customer = new BankingCustomer("just", "meadors", "meadors@yahoo.com",
				"333222444", "justinm", 262736.22,"Savings",363737);
		String Ssn = customer.getsSN();
		assertEquals(Ssn,Ssn);
	}
	
	@Test
	public void testForAccountSuccess() {
		Account account = new Account();
		BankingCustomer customer = new BankingCustomer("just", "meadors", "meadors@yahoo.com", "333222444", "justinm" ,account);
		account = customer.getAccount();
		assertEquals(account,account);
	}
	
	@Test
	public void testAccountType() {
		BankingCustomer customer = new BankingCustomer("just", "meadors", "meadors@yahoo.com",
				"333222444", "justinm", 262736.22,"Savings",363737);
		String accountType = customer.getAccountType();
		assertEquals(accountType,accountType);
	}
	
}