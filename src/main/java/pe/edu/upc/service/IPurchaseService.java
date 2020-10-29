package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.model.Purchase;

public interface IPurchaseService {
	
	public boolean insert(Purchase purchase);
	public Optional<Purchase> listId(int idPurchase);
	List<Purchase> list();
	
}
