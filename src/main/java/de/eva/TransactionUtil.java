package de.eva;

import java.util.Date;

import de.eva.Account.Transaction;
import de.eva.Account.Transaction.Person;

public class TransactionUtil {

	private Account account;
	private double deposit;

	public TransactionUtil(Account account, double deposit) {
		this.account = account;
		this.deposit = deposit;
	}
	
	void executeTransaction(Person pers) {
		Transaction transaction = PojoFactory.createTransaction(new Date().getTime(), 
															    deposit, 
															    pers);
		account.getTransaction().add(transaction);
	}

}
