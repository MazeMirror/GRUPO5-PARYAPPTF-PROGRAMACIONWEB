package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.Card;
import pe.edu.upc.repository.ICardRepository;
import pe.edu.upc.service.ICardService;


@Service
public class CardServiceImpl implements ICardService {
	
	@Autowired
	private ICardRepository caR;

	@Override
	@Transactional
	public boolean insert(Card card) {
		Card objCard = caR.save(card);
		if (objCard == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional	
	public boolean modificar(Card card) {
		boolean flag = false;
		try {
			caR.save(card);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("no se pudo modificar");
		}
		return flag;
	}

	@Override
	@Transactional
	public void delete(int idCard) {
		caR.deleteById(idCard);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Card> listId(int idCard) {
		return caR.findById(idCard);		
	}

	@Override
	@Transactional
	public List<Card> list() {
		return caR.findAll();
	}

	@Override
	@Transactional
	public List<Card> buscarCard(String nameCard) {
		return caR.buscarCard(nameCard);
	}
	
}

