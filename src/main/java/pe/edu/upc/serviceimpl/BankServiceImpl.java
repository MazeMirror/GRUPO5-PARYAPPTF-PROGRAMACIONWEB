package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import pe.edu.upc.model.Bank;
import pe.edu.upc.repository.IBankRepository;
import pe.edu.upc.service.IBankService;

@Service
public class BankServiceImpl implements IBankService{
	
	@Autowired
	private IBankRepository dBank;

	@Override
	@Transactional
	public boolean insert(Bank bank) {
		Bank objBank= dBank.save(bank);
		if(objBank == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public boolean update(Bank bank) {
		boolean flag = false;
		try {
			dBank.save(bank);
			flag = true;
		} catch (Exception e) {
			System.out.println("Succedio un error");
		}
		return flag;
	}

	@Override
	@Transactional
	public void delete(int idBank) {
		dBank.deleteById(idBank);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Bank> listId(int idBank) {
		return dBank.findById(idBank);
	}

	@Override
	@Transactional
	public List<Bank> list() {
		return dBank.findAll();
	}


}
