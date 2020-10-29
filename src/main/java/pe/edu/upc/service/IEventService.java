package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.model.Event;

public interface IEventService {
	
	public boolean insert(Event event);
	public boolean update(Event event);
	public void delete(int idEvent);
	public Optional<Event> listId(int idEvent);
	List<Event> list();
	List<Event> findName(String nameEvent);
}
