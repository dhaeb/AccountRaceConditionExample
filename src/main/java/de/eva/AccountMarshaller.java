package de.eva;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXB;

public class AccountMarshaller {

	private Account account;

	public AccountMarshaller(Account account) {
		this.account = account;
	}

	public void marshallToXml(String path) throws IOException{
		File xmlFile = createFileAlsoWhenNotExisting(path);
		JAXB.marshal(account, xmlFile);
	}

	private File createFileAlsoWhenNotExisting(String path) throws IOException {
		File fileHandler = new File(path);
		if(!fileHandler.exists()){
			fileHandler.createNewFile();
		}
		return fileHandler;
	}
	
	public void marshallToXml() throws IOException{
		marshallToXml("./target/account.xml");
	}
}
