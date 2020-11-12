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
import pe.edu.upc.service.IBankService;

@Controller
@RequestMapping("/bank")
public class BankController {

	@Autowired
	private IBankService bService;
	
	@RequestMapping("/welcome")
	public String irBankBienvenido() {
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irEvent(Map<String, Object> model) {
		model.put("listaBancos", bService.list());
		return "listBank";
	}
	
	@RequestMapping("/irRegister")
	public String irRegistrar(Model model) {
		model.addAttribute("bank", new Bank());
		return "bank";
	}
	
	@RequestMapping("/register")
	public String registrar(@ModelAttribute @Valid Bank objBank, BindingResult binRes, Model model) 
	throws ParseException
	{
		if (binRes.hasErrors()) {
			return "bank";
		}
		else {
			boolean flag = bService.insert(objBank);
			if (flag) {
				return "redirect:/bank/list";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/bank/irRegister";
			}
		}
	}
	
	@RequestMapping("/update/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
	throws ParseException
	{
		Optional<Bank> objBank = bService.listId(id);
		
		if(objBank == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/bank/list";
		}
		else {
			model.addAttribute("bank", objBank);
			return "bank";
		}
	}
	
	@RequestMapping("/delete")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id)
    throws ParseException{
		try {
			if(id!=null && id > 0) {
				bService.delete(id);
				model.put("listaBancos", bService.list());
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "sucedio un error");
			model.put("listaBancos", bService.list());
		}
		
		return "listBank";
	}
	
	@RequestMapping("/list")
	public String listar(Map<String, Object> model) {
		model.put("listaBancos", bService.list());
		return "listBank";
	}
	
	@RequestMapping("/listId")
	public String listar(Map<String, Object> model, @ModelAttribute Bank bank) 
	throws ParseException
	{
		bService.listId(bank.getIdBank());
		return "listBank";
	}
}
