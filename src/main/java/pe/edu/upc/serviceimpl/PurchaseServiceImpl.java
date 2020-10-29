package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.Purchase;
import pe.edu.upc.repository.IPurchaseRepository;
import pe.edu.upc.service.IPurchaseService;


@Service
public class PurchaseServiceImpl implements IPurchaseService{
	
	@Autowired
	private IPurchaseRepository dPurchase;

	@Override
	@Transactional
	public boolean insert(Purchase purchase) {
		Purchase objPurchase= dPurchase.save(purchase);
		if(objPurchase == null)
			return false;
		else
			return true;
	}

	
	@Override
	@Transactional(readOnly = true)
	public Optional<Purchase> listId(int idPurchase) {
		return dPurchase.findById(idPurchase);
	}

	@Override
	@Transactional
	public List<Purchase> list() {
		return dPurchase.findAll();
	}

}
