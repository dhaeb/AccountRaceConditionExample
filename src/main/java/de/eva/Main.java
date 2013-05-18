package de.eva;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		int max = 1000;
		int sependableBalence = max * (max + 1) / 2;	// Gauss 
		Account account = PojoFactory.createAccount(sependableBalence);
		
		for(int i = 1; i < 250 + 1; i++){
			new TransactionUtil(account, i).executeTransaction(PojoFactory.createPerson("Max", "Schwarz"));
		}
		for(int i = 251; i < 500 + 1; i++){
			new TransactionUtil(account, i).executeTransaction(PojoFactory.createPerson("Jane", "Doe"));
		}
		for(int i = 501; i < 750 + 1; i++){
			new TransactionUtil(account, i).executeTransaction(PojoFactory.createPerson("John", "Doe"));
		}
		for(int i = 751; i < max + 1; i++){
			new TransactionUtil(account, i).executeTransaction(PojoFactory.createPerson("Franklin", "Dilbert"));
		}
		
		System.out.println("finished program");
	}

}
