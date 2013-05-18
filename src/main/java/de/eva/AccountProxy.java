package de.eva;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

import de.eva.Account.Transaction;

public class AccountProxy implements InvocationHandler {
	
	private List<Transaction> transactionList;
	private Account account;

	public AccountProxy(List<Transaction> transactionList, Account account) {
		this.transactionList = transactionList;
		this.account = account;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object invokationResult = method.invoke(transactionList, args);
		if("add".equals(method.getName())){
			if(args[0] instanceof Transaction){
				invokeMarshalling(invokationResult, (Transaction) args[0]);
			}
		}
		return invokationResult;
	}

	private void invokeMarshalling(Object invokationResult, Transaction transaction) throws IOException {
		boolean result = (boolean) invokationResult;
		if(result){	// should be true all the time
			AccountMarshaller marshaller = new AccountMarshaller(account);
			account.setBalance(account.getBalance() - transaction.getValue());
			marshaller.marshallToXml();
		}
	}

}
