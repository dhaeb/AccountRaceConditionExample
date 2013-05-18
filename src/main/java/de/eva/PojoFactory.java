package de.eva;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.xml.datatype.XMLGregorianCalendar;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

import de.eva.Account.Transaction;
import de.eva.Account.Transaction.Person;

public class PojoFactory {
	
	private static Calendar CALENDER_INSTANCE = GregorianCalendar.getInstance();
	private static Person[] AVAILABLE_PERSONS = new Person[]{createPerson("Max", "Schwarz"), createPerson("Jane", "Doe"), createPerson("John", "Doe"), createPerson("Frank", "Dilbert")};

	public static Account createAccount(double balence) {
		Account returnable = new Account();
		//Race condition due to not threadsafe array list implementation
		returnable.transaction = new ArrayList<Transaction>();
		//vector is threadsafe 
		returnable.transaction = new Vector<Transaction>();
		returnable.balance = balence;
		return returnable;
	}

	public static Transaction createTransaction(long date, double deposit, Person pers){
		Transaction transaction = new Transaction();
		CALENDER_INSTANCE.setTime(new Date(date));
		XMLGregorianCalendar calender = new XMLGregorianCalendarImpl((GregorianCalendar) CALENDER_INSTANCE.clone());
		transaction.setDate(calender);
		transaction.setValue(deposit);
		transaction.setPerson(pers);
		return transaction;
	}
	
	public static Person createPerson(String firstName, String lastName){
		Person pers = new Person();
		pers.setFirstname(firstName);
		pers.setLastname(lastName);
		return pers;
	}
	
	public static Person createRandomPerson(){
		int availablePersonCount = AVAILABLE_PERSONS.length;
		return AVAILABLE_PERSONS[createRandomArrayIndex(availablePersonCount)]; 
	}

	private static int createRandomArrayIndex(int availablePersonCount) {
		return (int) Math.floor((availablePersonCount - 0.0001d) * Math.random());
	}
	
}
