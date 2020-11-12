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

import pe.edu.upc.model.Bank;
import pe.edu.upc.model.Card;
import pe.edu.upc.model.User;
import pe.edu.upc.service.IBankService;
import pe.edu.upc.service.ICardService;
import pe.edu.upc.service.IUserService;


@Controller
@RequestMapping("/card")
public class CardController {
	
@Autowired
private  ICardService caService;

@Autowired
private IUserService uService;

@Autowired
private IBankService bService;

@RequestMapping("/welcome")
public String irWelcome() {
	return "bienvenido";
}

@RequestMapping("/")
public String irCard(Map<String, Object> model) {
	model.put("listaTarjetas", caService.list());
	return "listCards";
}

/////////////////////////////

@RequestMapping("/list")
public String listar(Map<String, Object> model) {
	model.put("listaTarjetas", caService.list());
	return "listCards";
}

@RequestMapping("/listId")
public String listar(Map<String, Object> model, @ModelAttribute Card card) 
throws ParseException
{
	caService.listId(card.getIdCard());
	return "listCards";
}	

//////////////////////////////////

@RequestMapping("/delete")
public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) 
{
	try {
		if (id!=null && id > 0) {
			caService.delete(id);
			model.put("listaTarjetas", caService.list());
		}
	}
	catch(Exception ex) {
		System.out.println(ex.getMessage());
		model.put("mensaje", "no se pudo eliminar la tarjeta");
		model.put("listaTarjetas", caService.list());
	}
	return "listCards";
}

/////////////////////////

@RequestMapping("/modificar/{id}")
public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
throws ParseException
{
	Optional<Card> objCard = caService.listId(id);
	
	if (objCard == null) {
		objRedir.addFlashAttribute("mensaje", "No se pudo modificar la tarjeta");
		return "redirect:/card/listcard";
	}
	else {
		model.addAttribute("listaUsuarios", uService.list());
		model.addAttribute("listaBancos", bService.list());
		if(objCard.isPresent())
			objCard.ifPresent(o -> model.addAttribute("card", o));
		return "card";
	}
}


/////////////////

@RequestMapping("/irRegister")
public String irRegistrar(Model model) {
	model.addAttribute("listaBancos", bService.list());
	model.addAttribute("listaUsuarios", uService.list());
	model.addAttribute("bank", new Bank());
	model.addAttribute("user", new User());
	model.addAttribute("card", new Card());
	return "card";
}

@RequestMapping("/register")
public String registrar(@ModelAttribute @Valid Card objCard, BindingResult binRes, Model model) 
throws ParseException
{
	if (binRes.hasErrors()) {
		model.addAttribute("listaBancos", bService.list());
		model.addAttribute("listaUsuarios", uService.list());
		return "card";
	}
	else {
		boolean flag = caService.insert(objCard);
		if (flag) {
			return "redirect:/card/list";
		}
		else {
			model.addAttribute("mensaje", "No se pudo registrar la tarjeta");
			return "redirect:/card/irRegister";
		}
	}
}

////////////////////

}
