package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.model.Role;

public interface IRoleService {
	public int insert (Role _role);
	List <Role> list();
	
	public Role getrole(int idRole);
}
