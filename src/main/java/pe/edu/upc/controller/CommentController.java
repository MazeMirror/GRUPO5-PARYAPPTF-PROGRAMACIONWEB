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
import pe.edu.upc.model.Comment;
import pe.edu.upc.model.User;
import pe.edu.upc.service.IEventService;
import pe.edu.upc.service.ICommentService;
import pe.edu.upc.service.IUserService;

@Controller
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private ICommentService cService;
	
	@Autowired
	private IEventService eService;
	
	@Autowired
	private IUserService uService;
	
	@RequestMapping("/")
	public String irPurchaseDetail(Map<String, Object> model) {
		model.put("listaComentarios", cService.list());
		return "listComment";
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
		model.addAttribute("comment", new Comment());
		return "comment";
	}
	
	@RequestMapping("/register")
	public String registrar(@ModelAttribute @Valid Comment objComment, BindingResult binRes, Model model) 
	throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaEventos", eService.list());
			model.addAttribute("listaUsuarios", uService.list());
			return "comment";
		}
		else {
			boolean flag = cService.insert(objComment);
			if (flag) {
				return "redirect:/comment/list";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/comment/irRegister";
			}
		}
	}
	
	@RequestMapping("/update/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
	throws ParseException
	{
		Optional<Comment> objComment = cService.listId(id);
		
		if(objComment == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/comment/list";
		}
		else {
			model.addAttribute("listaUsuarios", uService.list());
			model.addAttribute("listaEventos", eService.list());
			if(objComment.isPresent())
				objComment.ifPresent(o -> model.addAttribute("comment", o));
			return "comment";
		}
	}
	
	@RequestMapping("/delete")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id)
    throws ParseException{
		try {
			if(id!=null && id > 0) {
				cService.delete(id);
				model.put("listaComentarios", cService.list());
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "sucedio un error");
			model.put("listaComentarios", cService.list());
		}
		
		return "listComment";
	}
	
	@RequestMapping("/list")
	public String listar(Map<String, Object> model) {
		model.put("listaComentarios", cService.list());
		return "listComment";
	}
	
	@RequestMapping("/listId")
	public String listar(Map<String, Object> model, @ModelAttribute Comment comment) 
	throws ParseException
	{
		cService.listId(comment.getIdComment());
		return "listComment";
	}
	
}
