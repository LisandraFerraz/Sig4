package com.fatec.Sig4.adapters;

import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.fatec.Sig4.model.Cliente;
import com.fatec.Sig4.ports.MantemCliente;

@Controller
@RequestMapping(path = "/sig")
public class GUIClienteController {
	Logger logger = LogManager.getLogger(GUIClienteController.class);
	@Autowired
	MantemCliente servico;

	@GetMapping("/clientes")
	public ModelAndView retornaFormDeConsultaTodosClientes() {
		ModelAndView modelAndView = new ModelAndView("consultarCliente");
		modelAndView.addObject("clientes", servico.consultaTodos());
		return modelAndView;
	}

	@GetMapping("/cliente")
	public ModelAndView retornaFormDeCadastroDe(Cliente cliente) {
		ModelAndView mv = new ModelAndView("cadastrarCliente");
		mv.addObject("cliente", cliente);
		return mv;
	}

	@GetMapping("/clientes/{cpf}") // diz ao metodo que ira responder a uma requisicao do tipo get
	public ModelAndView retornaFormParaEditarCliente(@PathVariable("cpf") String cpf) {
		ModelAndView modelAndView = new ModelAndView("atualizarCliente");
		modelAndView.addObject("cliente", servico.consultaPorCpf(cpf).get()); // retorna um objeto do tipo cliente
		return modelAndView; // addObject adiciona objetos para view
	}

	@GetMapping("/clientes/id/{id}")
	public ModelAndView excluirNoFormDeConsultaCliente(@PathVariable("id") Long id) {
		servico.delete(id);
		logger.info(">>>>>> 1. servico de exclusao chamado para o id => " + id);
		ModelAndView modelAndView = new ModelAndView("consultarCliente");
		modelAndView.addObject("clientes", servico.consultaTodos());
		return modelAndView;
	}

	@PostMapping("/clientes")
	public ModelAndView save(@Valid Cliente cliente, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("consultarCliente");
		if (result.hasErrors()) {
			modelAndView.setViewName("cadastrarCliente");
		} else {
			if (servico.save(cliente).isPresent()) {
				logger.info(">>>>>> controller chamou adastrar e consulta todos");
				modelAndView.addObject("clientes", servico.consultaTodos());
			} else {
				logger.info(">>>>>> controller cadastrar com dados invalidos");
				modelAndView.setViewName("cadastrarCliente");
				modelAndView.addObject("message", "Dados invalidos");
			}
		}
		return modelAndView;
	}

	@PostMapping("/clientes/id/{id}")
	public ModelAndView atualizaCliente(@PathVariable("id") Long id, @Valid Cliente cliente, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("consultarCliente");
		logger.info(">>>>>> servico para atualizacao de dados chamado para o id => " + id);
		if (result.hasErrors()) {
			logger.info(">>>>>> servico para atualizacao de dados com erro => " + result.getFieldError().toString());
			cliente.setId(id);
			return new ModelAndView("atualizarCliente");
		} else {
			servico.altera(cliente);
			modelAndView.addObject("clientes", servico.consultaTodos());
		}
		return modelAndView;
	}
}
