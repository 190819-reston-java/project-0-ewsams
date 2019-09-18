import java.util.ArrayList;
import java.util.Scanner;
import org.apache.log4j.Logger;
import com.revature.exception.IllegalInput;
import com.revature.model.Account;
import com.revature.model.BankingCustomer;
import com.revature.model.CheckingAccount;
import com.revature.model.SavingsAccount;


public class MenuPrompt {
	
	 public static boolean loggedIn = false;
	 public static int account;
	 int userInput;
	 public static Logger bankLogger = Logger.getLogger(MenuPrompt.class);
	 Scanner customerChoice = new Scanner(System.in);
	 int optionNotRecognizedCounter = 0;
	
	public void menu() throws Exception {
		System.out.println(" ******************************** ");
		System.out.println(" ******************************** ");
		System.out.println(" Welcome to E.W.S. & Liberty Bank ");
		System.out.println(" ******************************** ");
		System.out.println(" ******************************** ");
		System.out.println("Please select one of the following options");
		System.out.println(" 1 To create your Account");
		System.out.println(" 2 Returning BankingCustomer....Login to view Your Account Balance");
		System.out.println(" 3 To make a deposit");
		System.out.println(" 4 To make a withdrawl");
		System.out.println(" 5 To log out and exit");
		String usersOption = customerChoice.nextLine();
		int userInput = Integer.parseInt(usersOption);

		switch(userInput) {
		
		case 1:
			openAccount();
			break;
		
		case 2:
			loginAccount();
			break;
		
		case 3:
			makeDeposit();
			break;
		
		case 4:
			makeWithdrawl();
			break;
			
		case 5:
			System.out.println("Thankyou for Banking with Liberty please have a great day");
			System.exit(0);
		
		default:
			int countdown = 5 - optionNotRecognizedCounter;
			while(countdown > 0) {
			System.out.println("Option not recognized");
			optionNotRecognizedCounter ++;
			System.out.println("You have " + countdown  + " attempts before the closing of this application.");
			System.out.println("Returning you to the Menu....");
			menu();
			System.out.print("Too many errors have occured, Please try again later....");
			System.exit(0);
			bankLogger.fatal("Program encountered more than 5 errors. Program shutting down....");
			menu();
			break;
				}
			}
		}

	private void makeWithdrawl() throws Exception {
		if(loggedIn) {
			ArrayList<BankingCustomer> availableAccounts = Bank.getBankingCustomersList();
			for(int i=0; i<availableAccounts.size();i++) {
				if(MenuPrompt.account == availableAccounts.get(i).getAccount().getAccountNumber()){
						double amount = 0;
				try {
					System.out.println("Please specify the amount to withdraw");
					amount = Double.parseDouble(customerChoice.nextLine());
					availableAccounts.get(i).getAccount().withdraw(amount);
					System.out.println("Thank you for your business....");
					System.out.println("Returning you back to the Main Menu....");
					menu();
				}catch(NumberFormatException e) {
						System.out.println("Sorry please enter a number to deposit");
						bankLogger.info("NumberFormatException passed");
						}	
					}
					else {
						bankLogger.info("Failed to Login from loginAccount()");
						System.out.println("Sorry you are not logged In....");
						System.out.println("Returning you to the Main Menu");
						menu();
					}
				}
			}
		}

	private void openAccount () throws Exception {
		//this method functions as our Factory Method
		BankingCustomer customer;
		Account account = null;
		String typeOfAccount = "";
		String firstName = "";
		String lastName = "";
		String email = "";
		String password = "";
		String sSN = "";
		double initialDeposit = 0;
		System.out.println("Please enter the type of Account you would like to open");
		System.out.println("Checking or Savings");
		typeOfAccount = customerChoice.nextLine().toUpperCase();
			if(typeOfAccount.equals("CHECKING") || 
					typeOfAccount.equals("SAVINGS")) {
				System.out.println("Initializing a Checking account rquires an initial deposit of $50");
				System.out.println("Initializing a Savings account rquires an initial deposit of $100");
				System.out.println("Lets get started creating your " + typeOfAccount + " account");
			} else {
				System.out.println(" Incorrect entry....");
				openAccount();
			}
			System.out.println("Please enter your First Name");
			firstName = customerChoice.nextLine();
			System.out.println("Please enter your Last Name");
			lastName = customerChoice.nextLine();
			System.out.println("Please enter your email");
			email = customerChoice.nextLine();
			System.out.println("Please enter your Social Security Number");
			sSN = customerChoice.nextLine();
			System.out.println("Please enter your password");
			password = customerChoice.nextLine();
		try {
		
		if(sSN.length() == 8) {
			System.out.println("Thank you for your information");
				}
					} catch(Exception e) {
							System.out.println("Incorrect Information"
									+ "Please enter a valid Social Security Number....");
							bankLogger.info("NumberFormatException passed");
						}
		
			System.out.println("Please enter an Initial Deposit....");		
			try {
			initialDeposit = Double.parseDouble(customerChoice.nextLine());			
				}	
				catch(NumberFormatException e ) {
						System.out.println("Your initial Deposit must be a numerical value....");
					}
			if(typeOfAccount.equals("CHECKING") & initialDeposit <= 50){
				System.out.println("In order to initialize your new Checking Account your "
						+ "initial deposit must be equal to at least $50");
			}	else if(typeOfAccount.equals("SAVINGS") & initialDeposit <= 100) {
				System.out.println("In order to initialize your new Savings Account your "
					+ "initial deposit must be at least $100" + "Returning you to intial prompt...");
					openAccount();
						
					}	else if(typeOfAccount.equals("CHECKING") & initialDeposit >= 50) {
						account = new CheckingAccount(initialDeposit);
						customer = new BankingCustomer(firstName, lastName, email, sSN, password, account);
						Bank.addCustomer(customer);
						System.out.println("Thank you returning you to the menu");
						menu();
						}	else { 
							account = new SavingsAccount(initialDeposit);
							customer = new BankingCustomer(firstName, lastName, email, sSN, password, account);
							Bank.addCustomer(customer);
							System.out.println("Thank you returning you to the menu");
							menu();
							}
					}
		
	private void makeDeposit() throws Exception {
		if(loggedIn) {
			ArrayList<BankingCustomer> availableAccounts = Bank.getBankingCustomersList();
			for(int i=0; i<availableAccounts.size();i++) {
				if(MenuPrompt.account == availableAccounts.get(i).getAccount().getAccountNumber()){
						double amount = 0;
				try {
					System.out.println("Please specify the amount to deposit");
					amount = Double.parseDouble(customerChoice.nextLine());
					availableAccounts.get(i).getAccount().deposit(amount);
					System.out.println("Thank you for your business....");
					System.out.println("Returning you back to the Main Menu....");
					menu();
				}catch(IllegalInput e) {
						System.out.println("Sorry please enter a number to deposit");
						bankLogger.info("NumberFormatException passed");
						}	
					}
					else {
						bankLogger.info("Failed to recognize option 5 times....");
						System.out.println("Sorry you are not logged In....");
						System.out.println("Returning you to the Main Menu");
						menu();
					}
				}
			}
		}
		
	private void loginAccount() throws Exception {
		String firstName;
		String lastName;
		String accountType;
		String email;
		String password;
		ArrayList<BankingCustomer> availableAccounts = Bank.getBankingCustomersList();
		System.out.println("Please enter your First Name");
		firstName = customerChoice.nextLine();
		System.out.println("Please enter your Last Name");
		lastName = customerChoice.nextLine();
		System.out.println("Please enter the account type Checking or Savings");
		accountType = customerChoice.nextLine().toUpperCase();
		if(accountType.equals("CHECKING") || 
				accountType.equals("SAVINGS")) {
			System.out.println("Thank you we require a bit more information....");
			} else {
				int countdown = 5 - optionNotRecognizedCounter;
				while(countdown > 0) {
				System.out.println("Option not recognized");
				optionNotRecognizedCounter ++;
				System.out.println("You have " + countdown  + " attempts before the closing of this application.");
				System.out.println("Returning you to the Menu....");
				loginAccount();
				System.out.print("Too many errors have occured, Please try again later....");
				bankLogger.fatal("Failed to recognize option 5 times. Exiting our program....");
				System.exit(0);
				loginAccount();
				break;	
					}
			}
			System.out.println("Please enter your email");
			email = customerChoice.nextLine();
			System.out.println("Please enter your password");
			password = customerChoice.nextLine();
		if(availableAccounts.size()<=0) {
			System.out.println("Please create an account prior to logging in....");
			openAccount();
			}	else {
					for(int i=0; i<availableAccounts.size();i++) {
						if(firstName.equals(availableAccounts.get(i).getFirstName()) &  
						lastName.equals(availableAccounts.get(i).getLastName()) &
						email.equals(availableAccounts.get(i).getEmail()) & 	
						password.equals(availableAccounts.get(i).getPassword())) {
						System.out.println("Thank you for Logging In " + firstName );
						System.out.println("**************************************");
						System.out.println("Account Type " + accountType);
						System.out.println("------- Your current Balance is --    $" +
						availableAccounts.get(i).getAccount().getBalance());
						System.out.println("Your current Account Number is " +
						availableAccounts.get(i).getAccount().getAccountNumber());
						System.out.println("--------------------------------------");
						System.out.println("**************************************");
						System.out.println("Returning you to the Menu");
						System.out.println("**************************");
						loggedIn=true;
						bankLogger.info("Logged into Account");
						MenuPrompt.account = availableAccounts.get(i).getAccount().getAccountNumber();
						menu();
						}	
					}
				}
			}
	}
