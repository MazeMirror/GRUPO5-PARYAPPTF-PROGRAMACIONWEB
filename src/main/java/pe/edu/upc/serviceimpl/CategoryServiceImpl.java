package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import pe.edu.upc.model.Category;
import pe.edu.upc.repository.ICategoryRepository;
import pe.edu.upc.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService{
	
	@Autowired
	private ICategoryRepository dCategory;

	@Override
	@Transactional
	public boolean insert(Category category) {
		Category objCategory= dCategory.save(category);
		if(objCategory == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public boolean update(Category category) {
		boolean flag = false;
		try {
			dCategory.save(category);
			flag = true;
		} catch (Exception e) {
			System.out.println("Sucedio un error");
		}
		return flag;
	}

	@Override
	@Transactional
	public void delete(int idCategory) {
		dCategory.deleteById(idCategory);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Category> listId(int idCategory) {
		return dCategory.findById(idCategory);
	}

	@Override
	@Transactional
	public List<Category> list() {
		return dCategory.findAll();
	}


}
