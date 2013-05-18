package de.eva;

import java.util.ArrayList;
import java.util.List;

import de.eva.Account.Transaction;

public class Main {

	public static void main(String[] args) throws Exception {
		long max = 1000000;
		long countOfThreads = 1000;
		long maxThreadRatio = max / countOfThreads;
		long sependableBalence = max * (max + 1) / 2; // Gauss
		long startTime = System.currentTimeMillis();
		Account account = PojoFactory.createAccount(sependableBalence);
		List<Thread> threadList = new ArrayList<Thread>();
		for(int i = 0; i < countOfThreads; i++){
			threadList.add(new TransactionExecutor(account, 
												   1 + maxThreadRatio * i, 
												   maxThreadRatio * (i + 1), 
												   PojoFactory.createRandomPerson()));
		}
		for(Thread currentThread : threadList){
			currentThread.start();
		}
		for(Thread currentThread : threadList){
			currentThread.join();
		}
		account.setBalance(account.getBalance() - getTransactionValue(account));
		AccountMarshaller marshaller = new AccountMarshaller(account);
		marshaller.marshallToXml();
		long stopTime = System.currentTimeMillis();
		System.out.println("finished program, needed " + (stopTime - startTime) / 1000d + " seconds");
	}

	private static double getTransactionValue(Account account) {
		double transferedMoney = 0;
		for(Transaction currentTransaction : account.getTransaction()){
			try {
				transferedMoney += currentTransaction.getValue();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		return transferedMoney;
	}

}
