package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.model.User;

public interface IUserService {
	public boolean insert(User user);
	public boolean modify(User user);
	public void eliminate(int idUser);
	public Optional<User> listId(int idUser);
	List<User> list();
	List<User> findByName(String nameUser);
}
