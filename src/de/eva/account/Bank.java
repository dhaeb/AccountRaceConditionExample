package de.eva.account;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Bank extends Thread {
	
	private static List<Account> accounts = new ArrayList<Account>();
	
	public static void addAccount(Account account){
		accounts.add(account);
	}
	    
	public static void transfer(Account from, Account to, double amount) {
		from.setBalance(from.getBalance() - amount);
		to.setBalance(to.getBalance() + amount);
	}
	public static List<Account> getAccounts(){
		return accounts;
	}
	
	public static void printAccounts(){
	    for(Account account : accounts){
	    	  System.out.println("Balance of Account " + account.getId() + ": " + account.getBalance());
	    }
	}
	
	private Collection<Transaction> transactions;
	
	public Bank(Collection<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	@Override
	public void run() {
		for(Transaction currentTransaction : transactions){
			new Thread(currentTransaction).start();
		}
	}
}
