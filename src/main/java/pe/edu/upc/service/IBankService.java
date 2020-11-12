package pe.edu.upc.service;



import java.util.List;
import java.util.Optional;

import pe.edu.upc.model.Bank;



public interface IBankService {
	public boolean insert(Bank bank);
	public void delete(int idBank);
	public boolean update(Bank bank);
	public Optional<Bank> listId(int idBank);
	List<Bank> list();

}
