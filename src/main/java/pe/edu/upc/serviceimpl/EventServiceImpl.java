package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import pe.edu.upc.model.Event;
import pe.edu.upc.repository.IEventRepository;
import pe.edu.upc.service.IEventService;

@Service
public class EventServiceImpl implements IEventService{
	
	@Autowired
	private IEventRepository dEvent;

	@Override
	@Transactional
	public boolean insert(Event event) {
		Event objEvent= dEvent.save(event);
		if(objEvent == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public boolean update(Event event) {
		boolean flag = false;
		try {
			dEvent.save(event);
			flag = true;
		} catch (Exception e) {
			System.out.println("Succedio un error");
		}
		return flag;
	}

	@Override
	@Transactional
	public void delete(int idEvent) {
		dEvent.deleteById(idEvent);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Event> listId(int idEvent) {
		return dEvent.findById(idEvent);
	}

	@Override
	@Transactional
	public List<Event> list() {
		return dEvent.findAll();
	}

	@Override
	@Transactional
	public List<Event> findName(String nameEvent) {
		return dEvent.findName(nameEvent);
	}

}
