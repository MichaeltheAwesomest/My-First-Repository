package Assignment;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BankingSystem {
	static Scanner scanner = new Scanner(System.in);
	static final Map<String, Account> accounts = new HashMap<>();
	public static void main(String[] args) {
		while(true) {
			System.out.println("Welcome to Adeotex Banx.\nChoose an operation.");
			System.out.println("1.Create an account.\n2.Login.\n3.Close app.");
			String name;
			int pin;
			Account account;
			int operation = scanner.nextInt();
			scanner.nextLine();
			switch(operation) {
				case 1:
					System.out.println("Create an account");
					System.out.println("-----------------");
					System.out.print("Account Name: ");
					name = scanner.nextLine();
					if(accounts.containsKey(name)) {
						System.out.println("This name is taken.");
						continue;
					}
					System.out.print("Pin: ");
					pin = scanner.nextInt();
					account = new Account(name,pin);
					accounts.put(name,account);
					System.out.println(name + " you have successfully created your Adeotex account.");
					scanner.nextLine();
					break;
				case 2:
					System.out.println("Log in");
					System.out.println("------");
					System.out.print("Account Name: ");
					name = scanner.nextLine();
					
					if(!accounts.containsKey(name)) {
						System.out.println("Invalid Account name.");
						continue;
					}
					account = accounts.get(name);
					System.out.print("Pin: ");
					pin = scanner.nextInt();
					if(pin != account.pin) {
						System.out.println("Wrong pin.");
						continue;
					}
					account.accountOperations();
					break;
				case 3:
					System.out.println("Thank you for banking with us.");
					return;
				default:
					System.out.println("Hmmm...the options are 1, 2 and 3.");
			}
		}
	}
}

class Account {
	public Account(String name, int pin) {
		count++;
		id = count;
		balance = 0;
		this.name = name;
		this.pin = pin;
	}
	static int count;
	int id;
	String name;
	int pin;
	double balance;
	
	public void accountOperations() {
		System.out.println("Welcome " + name + ".");
		Map<String, Account> accounts = BankingSystem.accounts;
		Scanner scanner = BankingSystem.scanner;
		while(true) {
			scanner.nextLine();
			System.out.println("What would you like to do today?");
			System.out.println("1.Check balance.\n2.Deposit.\n3.Withdraw.\n4.Transfer.\n5.Log out.");
			String name;
			int pin;
			double amount;
			Account account;
			int operation = scanner.nextInt();
			scanner.nextLine();
			switch(operation) {
				case 1:
					System.out.println("You have ₦" + balance + " currently.");
					break;
				case 2:
					System.out.print("Amount: ");
					amount = scanner.nextDouble();
					if(amount < 0) {
						System.out.println("You want to donate money to bank? Tranfer don't press rubbish.");
						continue;
					}else if(amount == 0) {
						System.out.println("Waste of time, I ought to take 10k from you now.");
						continue;
					}
					this.balance += amount;
					System.out.println("You have deposited ₦" + amount + ".\nCurrent balance is ₦" + this.balance);
					break;
				case 3:
					System.out.print("Amount: ");
					amount = scanner.nextDouble();
					if(amount > this.balance) {
						System.out.println("LOL, Insufficient funds.");
						continue;
					}else if(amount == 0) {
						System.out.println("Waste of time, I ought to take 10k from you now.");
						continue;
					}else if(amount < 0) {
						System.out.println("I am calling police now.");
						continue;
					}
					this.balance -= amount;
					System.out.println("You have withdrawn ₦" + amount + ".\nCurrent balance is ₦" + this.balance);
					break;
				case 4:
					System.out.print("Account Name: ");
					name = scanner.nextLine();
					if(!accounts.containsKey(name)) {
						System.out.println("No such account, ensure your spelling and type are correct.");
						continue;
					}
					System.out.print("Amount: ");
					amount = scanner.nextDouble();
					if(amount > this.balance) {
						System.out.println("LOL, Insufficient funds.");
						continue;
					}else if(amount == 0) {
						System.out.println("Waste of time, I ought to take 10k from you now.");
						continue;
					}else if(amount < 0) {
						System.out.println("I am calling police now.");
						continue;
					}
					scanner.nextLine();
					System.out.print("Pin: ");
					pin = scanner.nextInt();
					if(pin != this.pin) {
						System.out.println("Wrong pin.");
						continue;
					}
					account = accounts.get(name);
					this.balance -= amount;
					account.balance += amount;
					System.out.println("You have successfully transferred ₦" + amount + "to " + account.name + ".\nCurrent balance is ₦" + this.balance);
					break;
				case 5:
					System.out.println("Thank you for banking with us.");
					return;
				default:
					System.out.println("Hmmm...the options are 1, 2 and 3.");
			}
		}
	}
}
