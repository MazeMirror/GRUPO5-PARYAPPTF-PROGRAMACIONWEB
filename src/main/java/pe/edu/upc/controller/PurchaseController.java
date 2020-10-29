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
import pe.edu.upc.model.Purchase;
import pe.edu.upc.model.User;
import pe.edu.upc.service.IEventService;
import pe.edu.upc.service.IPurchaseService;
import pe.edu.upc.service.IUserService;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

	@Autowired
	private IPurchaseService pService;
	
	@Autowired
	private IEventService eService;
	
	@Autowired
	private IUserService uService;
	
	@RequestMapping("/")
	public String irPurchaseDetail(Map<String, Object> model) {
		model.put("listaCompras", pService.list());
		return "listPurchase";
	}
	
	@RequestMapping("/welcome")
	public String irEventBienvenido() {
		return "bienvenido";
	}
	
	@RequestMapping("/irRegister")
	public String irRegistrar(Model model) {
		model.addAttribute("listaEventos", eService.list());
		model.addAttribute("listaUsuarios", uService.list());
		model.addAttribute("event", new Event());
		model.addAttribute("user", new User());
		model.addAttribute("purchase", new Purchase());
		return "purchase";
	}
	
	@RequestMapping("/register")
	public String registrar(@ModelAttribute @Valid Purchase objPurchase, BindingResult binRes, Model model) 
	throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaEventos", eService.list());
			model.addAttribute("listaUsuarios", uService.list());
			return "purchase";
		}
		else {
			boolean flag = pService.insert(objPurchase);
			if (flag) {
				return "redirect:/purchase/list";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/event/irRegister";
			}
		}
	}
	
	@RequestMapping("/list")
	public String listar(Map<String, Object> model) {
		model.put("listaCompras", pService.list());
		return "listPurchase";
	}
	
	@RequestMapping("/listId")
	public String listar(Map<String, Object> model, @ModelAttribute Purchase purchase) 
	throws ParseException
	{
		eService.listId(purchase.getIdPurchase());
		return "listPurchase";
	}
	
}
