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
	//Contraste
	@GetMapping("/loginContraste")
	public ModelAndView autenticacaoContraste() {
		return new ModelAndView("paginasContraste/paginaLoginContraste");
	}
//////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/")
	public ModelAndView home() {
		return new ModelAndView("paginaMenu");
	}
	//Contraste
	@GetMapping("/Contraste")
	public ModelAndView homeContraste() {
		return new ModelAndView("paginasContraste/paginaMenuContraste");
	}
//////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/tenis")
	public ModelAndView acessarTenis() {
		return new ModelAndView("tenis");
	}
	//Contraste
	@GetMapping("/tenisContraste")
	public ModelAndView acessarTenisContraste() {
		return new ModelAndView("paginasContraste/tenisContraste");
	}
//////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/equipamentos")
	public ModelAndView acessarEquipamentos() {
		return new ModelAndView("equipamentos");
	}
	//Contraste
	@GetMapping("/equipamentosContraste")
	public ModelAndView acessarEquipamentosContraste() {
		return new ModelAndView("paginasContraste/equipamentosContraste");
	}
//////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/acessibilidade")
	public ModelAndView acessarAcessibilidade() {
		return new ModelAndView("acessibilidade");
	}
	//Contraste
	@GetMapping("/acessibilidadeContraste")
	public ModelAndView acessarAcessibilidadeContraste() {
		return new ModelAndView("paginasContraste/acessibilidadeContraste");
	}
//////////////////////////////////////////////////////////////////////////////////
	@GetMapping("/carrinhoDeCompra")
	public ModelAndView acessarCarrinho() {
		return new ModelAndView("carrinhoDeCompra");
	}
	
	//Contraste
	@GetMapping("/carrinhoDeCompraContraste")
	public ModelAndView acessarCarrinhoContraste() {
		return new ModelAndView("paginasContraste/carrinhoDeCompraContraste");
	}
}
