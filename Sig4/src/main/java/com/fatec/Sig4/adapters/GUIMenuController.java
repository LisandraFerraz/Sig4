package com.fatec.Sig4.adapters;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GUIMenuController {
	@GetMapping("/login")
	public ModelAndView autenticacao() {
		return new ModelAndView("paginaLogin");
	}
	
	@GetMapping("/loginContraste")
	public ModelAndView autenticacaoContraste() {
		return new ModelAndView("paginaLoginContraste");
	}

	@GetMapping("/")
	public ModelAndView home() {
		return new ModelAndView("paginaMenu");
	}
	@GetMapping("/tenis")
	public ModelAndView acessarTenis() {
		return new ModelAndView("tenis");
	}
	
	@GetMapping("/equipamentos")
	public ModelAndView acessarEquipamentos() {
		return new ModelAndView("equipamentos");
	}
	@GetMapping("/acessibilidade")
	public ModelAndView acessarAcessibilidade() {
		return new ModelAndView("acessibilidade");
	}

	
	

}
