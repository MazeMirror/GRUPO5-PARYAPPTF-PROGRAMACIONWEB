package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.edu.upc.model.Message;
import pe.edu.upc.model.User;
import pe.edu.upc.service.IMessageService;
import pe.edu.upc.service.IUserService;

@Controller
@RequestMapping("/message")
public class MessageController {
	
	@Autowired
	private IMessageService mService;
	
	@Autowired
	private IUserService uService;
	
	@RequestMapping("/bienvenido")
	public String irMessageBienvenido() {
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irMessage(Map<String, Object> model) {
		model.put("listaMensajes", mService.list());
		return "listMessage";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("listaUsuarios", uService.list());
		model.addAttribute("user", new User());
		model.addAttribute("message", new Message());
		return "message";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Message objMessage, BindingResult binRes, Model model) 
	throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaUsuarios", uService.list());
			return "message";
		}
		else {
			boolean flag = mService.insert(objMessage);
			if (flag) {
				return "redirect:/message/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un problema");
				return "redirect:/message/irRegistrar";
			}
		}
	}
	
	
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) 
	{
		try {
			if (id!=null && id > 0) {
				mService.eliminate(id);
				model.put("listaMensajes", mService.list());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Sucedio un error");
			model.put("listaMensajes", mService.list());
		}
		return "listMessage";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaMensajes", mService.list());
		return "listMessage";
	}
	
	@RequestMapping("/listarId")
	public String listar(Map<String, Object> model, @ModelAttribute Message message) 
	throws ParseException
	{
		mService.listId(message.getIdMessage());
		return "listMessage";
	}	
	
	
	@RequestMapping("/search")
    public String buscar(Map<String, Object> model, Model modelo, @ModelAttribute Message message)
    throws ParseException {
		modelo.addAttribute("message", new Message());
        List<Message> listaMensajes;
        message.setSubject(message.getSubject());
        listaMensajes = mService.findBySubject(message.getSubject());

        if(listaMensajes.isEmpty()) {
            model.put("mensaje", "no se encontro");
        }
        model.put("listaMensajes", listaMensajes);
        return "listMessage";
    }

    @RequestMapping("/irSearch")
    public String irBuscar(Model model) {
        model.addAttribute("message", new Message());
        return "searchMessage";
    }
	
}
