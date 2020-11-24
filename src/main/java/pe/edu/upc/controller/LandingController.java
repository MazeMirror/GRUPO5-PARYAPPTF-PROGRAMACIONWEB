package pe.edu.upc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/landing")
public class LandingController {


	@RequestMapping("/index")
	public String irIndex() {
		return "landing/index";
	}
	
	@RequestMapping("/registro")
	public String irRegistro() {
		return "landing/registro";
	}
	
	@RequestMapping("/cliente")
	public String irCliente() {
		return "landing/Cliente";
	}
	
	@RequestMapping("/organizador")
	public String irOrganizador() {
		return "landing/Organizador";
	}
	
	@RequestMapping("/patrocinador")
	public String irPatrocinador() {
		return "landing/Patrocinador";
	}
	
	@RequestMapping("/contacto")
	public String irContacto() {
		return "landing/contacto";
	}
}
