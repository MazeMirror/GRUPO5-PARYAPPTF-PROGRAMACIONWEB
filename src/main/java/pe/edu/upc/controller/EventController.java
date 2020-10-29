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

import pe.edu.upc.model.Event;
import pe.edu.upc.model.User;
import pe.edu.upc.service.IEventService;
import pe.edu.upc.service.IUserService;

@Controller
@RequestMapping("/event")
public class EventController {

	@Autowired
	private IEventService eService;
	
	@Autowired
	private IUserService uService;
	
	@RequestMapping("/welcome")
	public String irEventBienvenido() {
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irEvent(Map<String, Object> model) {
		model.put("listaEventos", eService.list());
		return "listEvent";
	}
	
	@RequestMapping("/irRegister")
	public String irRegistrar(Model model) {
		model.addAttribute("listaUsuarios", uService.list());
		model.addAttribute("user", new User());
		model.addAttribute("event", new Event());
		return "event";
	}
	
	@RequestMapping("/register")
	public String registrar(@ModelAttribute @Valid Event objEvent, BindingResult binRes, Model model) 
	throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaUsuarios", uService.list());
			return "event";
		}
		else {
			boolean flag = eService.insert(objEvent);
			if (flag) {
				return "redirect:/event/list";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/event/irRegister";
			}
		}
	}
	
	@RequestMapping("/update/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
	throws ParseException
	{
		Optional<Event> objEvent = eService.listId(id);
		
		if(objEvent == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/event/list";
		}
		else {
			model.addAttribute("listaUsuarios", uService.list());
			if(objEvent.isPresent())
				objEvent.ifPresent(o -> model.addAttribute("event", o));
			return "event";
		}
	}
	
	@RequestMapping("/delete")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id)
    throws ParseException{
		try {
			if(id!=null && id > 0) {
				eService.delete(id);
				model.put("listaEventos", eService.list());
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "sucedio un error");
			model.put("listaEventos", eService.list());
		}
		
		return "listEvent";
	}
	
	@RequestMapping("/list")
	public String listar(Map<String, Object> model) {
		model.put("listaEventos", eService.list());
		return "listEvent";
	}
	
	@RequestMapping("/listId")
	public String listar(Map<String, Object> model, @ModelAttribute Event event) 
	throws ParseException
	{
		eService.listId(event.getIdEvent());
		return "listEvent";
	}
	
	@RequestMapping("/search")
	public String buscar(Map<String, Object> model, @ModelAttribute Event event)
	throws ParseException {
		List<Event> listaEventos;
		event.setNameEvent(event.getNameEvent());
		listaEventos = eService.findName(event.getNameEvent());
		
		if(listaEventos.isEmpty()) {
			model.put("mensaje", "no se encontro");
		}
		model.put("listaEventos", listaEventos);
		return "search";
	}
	
	@RequestMapping("/irSearch")
	public String irBuscar(Model model) {
		model.addAttribute("event", new Event());
		return "search";
	}
}
