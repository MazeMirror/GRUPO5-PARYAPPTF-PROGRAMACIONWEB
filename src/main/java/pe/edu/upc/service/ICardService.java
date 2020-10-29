package pe.edu.upc.service;



import java.util.List;
import java.util.Optional;

import pe.edu.upc.model.Card;



public interface ICardService {
	public boolean insert(Card card);
	public void delete(int idcard);
	public boolean modificar(Card card);
	public Optional<Card> listId(int idCard);
	List<Card> buscarCard(String nameCard);
	List<Card>list();

}
