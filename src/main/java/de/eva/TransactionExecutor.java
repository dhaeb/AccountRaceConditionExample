package de.eva;


import de.eva.Account.Transaction.Person;

public class TransactionExecutor extends Thread {

	private Account account;
	private long lowerBound;
	private long upperBound;
	private Person person;

	public TransactionExecutor(Account account, long lowerBound, long upperBound, Person person) {
		this.account = account;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.person = person;
	}
	
	@Override
	public void run() {
		for(long i = lowerBound; i < upperBound + 1; i++){
			new TransactionUtil(account, i).executeTransaction(person);
		}
	}

}
