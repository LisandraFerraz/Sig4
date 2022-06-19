package com.fatec.Sig4.adapters;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Io;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fatec.Sig4.model.Produto;
import com.fatec.Sig4.ports.MantemProduto;

@Controller
@RequestMapping(path = "/sig")
public class GUIProdutoController {
	private static String caminhoImagens = "/home/renan/git/Sig4/src/main/resources/static/imagem/ProdutoCadastrado/";

	Logger logger = LogManager.getLogger(GUIProdutoController.class);
	@Autowired
	MantemProduto servico;

	@GetMapping("/produtos")
	public ModelAndView retornaFormDeConsultaTodosProdutos() {
		ModelAndView modelAndView = new ModelAndView("consultarProduto");
		modelAndView.addObject("produtos", servico.consultaTodos());
		return modelAndView;
	}

	@GetMapping("/produto")
	public ModelAndView retornaFormDeCadastroDe(Produto produto) {
		ModelAndView mv = new ModelAndView("cadastrarProduto");
		mv.addObject("produto", produto);
		return mv;
	}

	@GetMapping("/produtos/{id}") // diz ao metodo que ira responder a uma requisicao do tipo get
	public ModelAndView retornaFormParaEditarProduto(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("atualizarProduto");
		modelAndView.addObject("produto", servico.consultaPorId(id).get()); // retorna um objeto do tipo cliente
		return modelAndView; // addObject adiciona objetos para view
	}	

	@GetMapping("/produtos/id/{id}")
	public ModelAndView excluirNoFormDeConsultaProduto(@PathVariable("id") Long id) {
		servico.delete(id);
		logger.info(">>>>>> 1. servico de exclusao chamado para o id => " + id);
		ModelAndView modelAndView = new ModelAndView("consultarProduto");
		modelAndView.addObject("produtos", servico.consultaTodos());
		return modelAndView;
	}

	@GetMapping("/produtos/mostrarImagem/{imagem}")
	@ResponseBody
	public byte[] retornarImagem(@PathVariable("imagem") String imagem) throws IOException {
		File imagemArquivo = new File(caminhoImagens+imagem);
		if(imagem!=null || imagem.trim().length()>0){
		
		return Files.readAllBytes(imagemArquivo.toPath());
		}
		return null;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	@PostMapping("/produtos")
	public ModelAndView save(@Valid Produto produto, BindingResult result,
	@RequestParam("file")MultipartFile arquivo) throws IOException {
	
		ModelAndView modelAndView = new ModelAndView(  "consultarProduto");
		if (result.hasErrors()) {
			modelAndView.setViewName("cadastrarProduto");
			return modelAndView;
		}
		if(!arquivo.isEmpty()){
			produto.setNomeImagem(arquivo.getName());
			byte[] bytes = arquivo.getBytes();
			Path caminho = Paths.get(caminhoImagens+arquivo.getName());
			Files.write(caminho, bytes);

			
		}

		if (servico.save(produto).isPresent()) {
			logger.info(">>>>>> controller chamou adastrar e consulta todos");
			modelAndView.addObject("produtos", servico.consultaTodos());
		} else {
			logger.info(">>>>>> controller cadastrar com dados invalidos");
			modelAndView.setViewName("cadastrarProduto");
			modelAndView.addObject("message", "Dados invalidos");
		}
	
		
		return modelAndView;
	}
	@PostMapping("/produtos/id/{id}")
	public ModelAndView atualizaProduto(@PathVariable("id") Long id, @Valid Produto produto, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("consultarProduto");
		logger.info(">>>>>> servico para atualizacao de dados chamado para o id => " + id);
		if (result.hasErrors()) {
			logger.info(">>>>>> servico para atualizacao de dados com erro => " + result.getFieldError().toString());
			produto.setId(id);
			return new ModelAndView("atualizarProduto");
		} 
		
		servico.altera(produto);
		modelAndView.addObject("produtos", servico.consultaTodos());
	
		return modelAndView;
	}
	
}