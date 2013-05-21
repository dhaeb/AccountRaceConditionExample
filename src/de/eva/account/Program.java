package de.eva.account;

import java.util.ArrayList;
import java.util.List;


public class Program {

	public static void main(String[] args) throws InterruptedException {
		
	      //Create Accounts and allocate them to the Bank
		  Account account1 = new Account(1, "Account 1", 75007500), 
				  account2 = new Account(2, "Account 2", 0d), 
				  account3 = new Account(3, "Account 3", 0d);
	      Bank.addAccount(account1);
	      Bank.addAccount(account2);
	      Bank.addAccount(account3);
	      
	      System.out.println("Before transaction:");
	      Bank.printAccounts();
		// Perform transactions
	      // Assume that employees perform transaction simultaneously
	      
	      	//create Thread lists
	      List<Transaction> firstThreadList = new ArrayList<Transaction>();
	      List<Transaction> secondThreadList = new ArrayList<Transaction>();
	      for(int i = 1; i <= 10000; i++){
	    	  Transaction trans = new Transaction(account1, account2, i * 1d); 
	    	  firstThreadList.add(trans);
	    	  Transaction trans2 = new Transaction(account1, account3, i * 0.5d); 
	    	  secondThreadList.add(trans2);
	      }
	      
	      Bank bank1 = new Bank(firstThreadList);
	      Bank bank2 = new Bank(secondThreadList);
	      bank1.start();
	      bank2.start();
	      bank1.join();
	      bank2.join();
	      System.out.println("After transaction: ");
	      Bank.printAccounts();
	}

}
