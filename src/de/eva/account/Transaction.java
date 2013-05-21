package de.eva.account;

public class Transaction implements Runnable {

	private Account from;
	private Account to;
	private double amount;

	public Transaction(Account from, Account to, double amount) {
		this.from = from;
		this.to = to;
		this.amount = amount;
	}

	public void run() {
		// Perform transaction
		Bank.transfer(from, to, amount);
	}

}
