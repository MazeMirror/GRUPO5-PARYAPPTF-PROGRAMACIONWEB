package pe.edu.upc.service;



import java.util.List;
import java.util.Optional;

import pe.edu.upc.model.Category;



public interface ICategoryService {
	public boolean insert(Category category);
	public void delete(int idCategory);
	public boolean update(Category category);
	public Optional<Category> listId(int idCategory);
	List<Category> list();

}
