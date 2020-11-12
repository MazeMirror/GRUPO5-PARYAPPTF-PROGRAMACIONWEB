package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.model.Message;

public interface IMessageService {
	public boolean insert(Message message);
	public void eliminate(int idMessage);
	public Optional<Message> listId(int idMessage);
	List<Message> list();
	List<Message> findBySubject(String subject);
	List<Message> findByUser(String nameUser);
}
