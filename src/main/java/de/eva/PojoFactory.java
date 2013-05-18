package de.eva;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

import de.eva.Account.Transaction;
import de.eva.Account.Transaction.Person;

public class PojoFactory {
	
	private static Calendar CALENDER_INSTANCE = GregorianCalendar.getInstance();

	@SuppressWarnings("unchecked")
	public static Account createAccount(double balence) {
		Account returnable = new Account();
		//Serialize List after every add method
		returnable.transaction = (List<Transaction>) Proxy.newProxyInstance(List.class.getClassLoader(), 
							   								new Class[]{List.class}, 
							   								new AccountProxy(new ArrayList<Transaction>(), returnable));
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
	
}
