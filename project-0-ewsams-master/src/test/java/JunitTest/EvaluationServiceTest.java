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

	
}