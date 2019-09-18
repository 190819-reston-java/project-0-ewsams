public class Account {

	private double balance;
	private String accountType;
	private int accountNumber = hashCode();
	public static int numberOfAccounts = 0;

	public Account() {
		this.accountNumber = hashCode() + numberOfAccounts++;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber() {
		this.accountNumber = hashCode();
	}

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

	public void deposit(double amount) throws Exception{
		if(amount <= 0) {
			System.out.println("Please input a valid amount to deposit....");
			}else{	balance += amount;
				System.out.println("--------------------------------------");
				System.out.println("You have deposited $" + amount);
				System.out.println("Your new current Balance is $"+ balance);
				System.out.println("***************************************");
			}
		}

	public void withdraw(double amount) {
		if(amount > balance) {
			System.out.println("Insufficient Funds ....");
			return;
			}else {
				balance -= amount;
				System.out.println("--------------------------------------");
				System.out.println("You have withdrawn $" + amount);
				System.out.println("Your new current Balance is $"+ balance);
				System.out.println("***************************************");
		}
	}
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numberOfAccounts++;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountNumber != other.accountNumber)
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		return true;
	}
}

