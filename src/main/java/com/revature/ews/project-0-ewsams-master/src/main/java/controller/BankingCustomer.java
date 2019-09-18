public class BankingCustomer {
	
	private final String firstName;
	private final String lastName;
	private final String email;
	private final String sSN;
	private final String password;
	private final Account account;

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
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.sSN = sSN;
		this.password = password;
		this.account = account;
	}

	@Override
	public String toString() {
		return "Customer [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", sSN=" + sSN
				+ ", password=" + password + ", account=" + account + "]";
	}
}

