package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.User;
import pe.edu.upc.repository.IUserRepository;
import pe.edu.upc.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserRepository dUser;

	@Override
	@Transactional
	public boolean insert(User user) {
		User objUser = dUser.save(user);
		if (objUser == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional	
	public boolean modify(User user) {
		boolean flag = false;
		try {
			dUser.save(user);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Hubo un problema");
		}
		return flag;
	}

	@Override
	@Transactional
	public void eliminate(int idUser) {
		dUser.deleteById(idUser);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> listId(int idUser) {
		return dUser.findById(idUser);		
	}

	@Override
	@Transactional
	public List<User> list() {
		return dUser.findAll();
	}

	@Override
	@Transactional
	public List<User> findByName(String nameUser) {
		return dUser.buscarNombre(nameUser);
	}
	
}
