package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.Message;
import pe.edu.upc.repository.IMessageRepository;
import pe.edu.upc.service.IMessageService;

@Service
public class MessageServiceImpl implements IMessageService {
	
	@Autowired
	private IMessageRepository dMessage;

	@Override
	@Transactional
	public boolean insert(Message message) {
		Message objMessage = dMessage.save(message);
		if (objMessage == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminate(int idMessage) {
		dMessage.deleteById(idMessage);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Message> listId(int idMessage) {
		return dMessage.findById(idMessage);		
	}

	@Override
	@Transactional
	public List<Message> list() {
		return dMessage.findAll();
	}

	@Override
	public List<Message> findBySubject(String subject) {
		return dMessage.buscarAsunto(subject);
	}

	@Override
	public List<Message> findByUser(String nameUser) {
		return dMessage.buscarUsuario(nameUser);
	}

	
}
