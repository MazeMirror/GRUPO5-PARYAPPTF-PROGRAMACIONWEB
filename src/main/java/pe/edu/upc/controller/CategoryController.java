package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.model.Category;
import pe.edu.upc.service.ICategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private ICategoryService cService;
	
	@RequestMapping("/welcome")
	public String irCategoryBienvenido() {
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irEvent(Map<String, Object> model) {
		model.put("listaCategorias", cService.list());
		return "listCategory";
	}
	
	@RequestMapping("/irRegister")
	public String irRegistrar(Model model) {
		model.addAttribute("category", new Category());
		return "category";
	}
	
	@RequestMapping("/register")
	public String registrar(@ModelAttribute @Valid Category objCategory, BindingResult binRes, Model model) 
	throws ParseException
	{
		if (binRes.hasErrors()) {
			return "category";
		}
		else {
			boolean flag = cService.insert(objCategory);
			if (flag) {
				return "redirect:/category/list";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/category/irRegister";
			}
		}
	}
	
	@RequestMapping("/update/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
	throws ParseException
	{
		Optional<Category> objCategory = cService.listId(id);
		
		if(objCategory == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/category/list";
		}
		else {
			model.addAttribute("category", objCategory);
			return "category";
		}
	}
	
	@RequestMapping("/delete")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id)
    throws ParseException{
		try {
			if(id!=null && id > 0) {
				cService.delete(id);
				model.put("listaCategorias", cService.list());
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "sucedio un error");
			model.put("listaCategorias", cService.list());
		}
		
		return "listCategory";
	}
	
	@RequestMapping("/list")
	public String listar(Map<String, Object> model) {
		model.put("listaCategorias", cService.list());
		return "listCategory";
	}
	
	@RequestMapping("/listId")
	public String listar(Map<String, Object> model, @ModelAttribute Category category) 
	throws ParseException
	{
		cService.listId(category.getIdCategory());
		return "listCategory";
	}
}
