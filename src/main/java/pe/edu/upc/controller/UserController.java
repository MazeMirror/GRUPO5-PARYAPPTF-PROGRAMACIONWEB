package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;
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

import pe.edu.upc.model.User;
import pe.edu.upc.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService uService;
	
	@RequestMapping("/bienvenido")
	public String irUserBienvenido() {
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irUser(Map<String, Object> model) {
		model.put("listaUsuarios", uService.list());
		return "listUser";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("user", new User());
		return "user";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid User objUser, BindingResult binRes, Model model) 
	throws ParseException
	{
		if (binRes.hasErrors()) {
			return "user";
		}
		else {
			boolean flag = uService.insert(objUser);
			if (flag) {
				return "redirect:/user/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un problema");
				return "redirect:/user/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
	throws ParseException
	{
		Optional<User> objUser = uService.listId(id);
		
		if (objUser == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un problema");
			return "redirect:/user/listar";
		}
		else {
			model.addAttribute("user", objUser);
			return "user";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) 
	{
		try {
			if (id!=null && id > 0) {
				uService.eliminate(id);
				model.put("listaUsuarios", uService.list());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Sucedio un error");
			model.put("listaUsuarios", uService.list());
		}
		return "listUser";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaUsuarios", uService.list());
		return "listUser";
	}
	
	@RequestMapping("/listarId")
	public String listar(Map<String, Object> model, @ModelAttribute User user) 
	throws ParseException
	{
		uService.listId(user.getIdUser());
		return "listUser";
	}	
	
	@RequestMapping("/search")
    public String buscar(Map<String, Object> model, @ModelAttribute User user)
    throws ParseException {
        List<User> listaUsuarios;
        user.setNameUser(user.getNameUser());
        listaUsuarios = uService.findByName(user.getNameUser());

        if(listaUsuarios.isEmpty()) {
            model.put("mensaje", "no se encontro");
        }
        model.put("listaUsuarios", listaUsuarios);
        return "listUser";
    }

    @RequestMapping("/irSearch")
    public String irBuscar(Model model) {
        model.addAttribute("user", new User());
        return "searchUser";
    }
	
}
