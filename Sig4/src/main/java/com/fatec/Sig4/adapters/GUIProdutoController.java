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

import com.fatec.Sig4.model.Produto;
import com.fatec.Sig4.ports.MantemProduto;

@Controller
@RequestMapping(path = "/sig")
public class GUIProdutoController {
	Logger logger = LogManager.getLogger(GUIProdutoController.class);
	@Autowired
	MantemProduto servico;

	@GetMapping("/produtos")
	public ModelAndView retornaFormDeConsultaTodosProdutos() {
		ModelAndView modelAndView = new ModelAndView("consultarProduto");
		modelAndView.addObject("produtos", servico.consultaTodos());
		return modelAndView;
	}
	
	//Contraste
	@GetMapping("/produtosContraste")
	public ModelAndView retornaFormDeConsultaTodosProdutosContraste() {
		ModelAndView modelAndView = new ModelAndView("paginasContraste/consultarProdutoContraste");
		modelAndView.addObject("produtosContraste", servico.consultaTodos());
		return modelAndView;
	}

	////////////////////////////////////////////////////////////////////////////////

	@GetMapping("/produto")
	public ModelAndView retornaFormDeCadastroDe(Produto produto) {
		ModelAndView mv = new ModelAndView("cadastrarProduto");
		mv.addObject("produto", produto);
		return mv;
	}
	
	//Contraste
	@GetMapping("/produtoContraste")
	public ModelAndView retornaFormDeCadastroDeContraste(Produto produto) {
		ModelAndView mv = new ModelAndView("paginasContraste/cadastrarProdutoContraste");
		mv.addObject("produtoContraste", produto);
		return mv;
	}
	
	////////////////////////////////////////////////////////////////////////////////

	@GetMapping("/produtos/{id}") // diz ao metodo que ira responder a uma requisicao do tipo get
	public ModelAndView retornaFormParaEditarProduto(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("atualizarProduto");
		modelAndView.addObject("produto", servico.consultaPorId(id).get()); // retorna um objeto do tipo cliente
		return modelAndView; // addObject adiciona objetos para view
	}	
	//Contraste
	@GetMapping("/produtosContraste/{id}") // diz ao metodo que ira responder a uma requisicao do tipo get
	public ModelAndView retornaFormParaEditarProdutoContraste(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("paginasContraste/atualizarProdutoContraste");
		modelAndView.addObject("produto", servico.consultaPorId(id).get()); // retorna um objeto do tipo cliente
		return modelAndView; // addObject adiciona objetos para view
	}

	
	////////////////////////////////////////////////////////////////////////////////

	
	@GetMapping("/produtos/id/{id}")
	public ModelAndView excluirNoFormDeConsultaProduto(@PathVariable("id") Long id) {
		servico.delete(id);
		logger.info(">>>>>> 1. servico de exclusao chamado para o id => " + id);
		ModelAndView modelAndView = new ModelAndView("consultarProduto");
		modelAndView.addObject("produtos", servico.consultaTodos());
		return modelAndView;
	}
	
	//Contraste
	@GetMapping("/produtosContraste/id/{id}")
	public ModelAndView excluirNoFormDeConsultaProdutoContraste(@PathVariable("id") Long id) {
		servico.delete(id);
		logger.info(">>>>>> 1. servico de exclusao chamado para o id => " + id);
		ModelAndView modelAndView = new ModelAndView("paginasContraste/consultarProdutoContraste");
		modelAndView.addObject("produtosContraste", servico.consultaTodos());
		return modelAndView;
	}

	////////////////////////////////////////////////////////////////////////////////
	@PostMapping("/produtos")
	public ModelAndView save(@Valid Produto produto, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("consultarProduto");
		if (result.hasErrors()) {
			modelAndView.setViewName("cadastrarProduto");
		} else {
			if (servico.save(produto).isPresent()) {
				logger.info(">>>>>> controller chamou adastrar e consulta todos");
				modelAndView.addObject("produtos", servico.consultaTodos());
			} else {
				logger.info(">>>>>> controller cadastrar com dados invalidos");
				modelAndView.setViewName("cadastrarProduto");
				modelAndView.addObject("message", "Dados invalidos");
			}
		}
		return modelAndView;
	}
	
	//Contraste
	@PostMapping("/produtosContraste")
	public ModelAndView saveContraste(@Valid Produto produto, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("paginasContraste/consultarProdutoContraste");
		if (result.hasErrors()) {
			modelAndView.setViewName("paginasContraste/cadastrarProdutoContraste");
		} else {
			if (servico.save(produto).isPresent()) {
				logger.info(">>>>>> controller chamou adastrar e consulta todos");
				modelAndView.addObject("produtosContraste", servico.consultaTodos());
			} else {
				logger.info(">>>>>> controller cadastrar com dados invalidos");
				modelAndView.setViewName("paginasContraste/cadastrarProdutoContraste");
				modelAndView.addObject("message", "Dados invalidos");
			}
		}
		return modelAndView;
	}
	////////////////////////////////////////////////////////////////////////////////

	@PostMapping("/produtos/id/{id}")
	public ModelAndView atualizaProduto(@PathVariable("id") Long id, @Valid Produto produto, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("consultarProduto");
		logger.info(">>>>>> servico para atualizacao de dados chamado para o id => " + id);
		if (result.hasErrors()) {
			logger.info(">>>>>> servico para atualizacao de dados com erro => " + result.getFieldError().toString());
			produto.setId(id);
			return new ModelAndView("atualizarProduto");
		} else {
			servico.altera(produto);
			modelAndView.addObject("produtos", servico.consultaTodos());
		}
		return modelAndView;
	}
	
	//Contraste
	@PostMapping("/produtosContraste/id/{id}")
	public ModelAndView atualizaProdutoContraste(@PathVariable("id") Long id, @Valid Produto produto, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("paginasContraste/consultarProdutoContraste");
		logger.info(">>>>>> servico para atualizacao de dados chamado para o id => " + id);
		if (result.hasErrors()) {
			logger.info(">>>>>> servico para atualizacao de dados com erro => " + result.getFieldError().toString());
			produto.setId(id);
			return new ModelAndView("paginasContraste/atualizarProdutoContraste");
		} else {
			servico.altera(produto);
			modelAndView.addObject("produtosContraste", servico.consultaTodos());
		}
		return modelAndView;
	}
}