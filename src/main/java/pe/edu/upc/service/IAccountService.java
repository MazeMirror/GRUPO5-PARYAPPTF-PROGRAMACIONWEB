package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.model.Account;

public interface IAccountService {
	
	public int insert (Account account);
	
	List <Account> list();
	
	public Account getAccount(String correo);
}
