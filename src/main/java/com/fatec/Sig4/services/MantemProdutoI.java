package com.fatec.Sig4.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.Sig4.model.Produto;
import com.fatec.Sig4.ports.MantemProduto;
import com.fatec.Sig4.ports.ProdutoRepository;

@Service
public class MantemProdutoI implements MantemProduto {
	Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	ProdutoRepository repository;

	@Override
	public List<Produto> consultaTodos() {
		logger.info(">>>>>> servico consultaTodos chamado");
		return repository.findAll();
	}

	@Override
	public Optional<Produto> consultaPorId(Long id) {
		logger.info(">>>>>> servico consultaPorId chamado");
		return repository.findById(id);
	}

	@Override
	public Optional<Produto> save(Produto produto) {
		logger.info(">>>>>> servico save chamado ");
		return Optional.ofNullable(repository.save(produto));
	}

	@Override
	public void delete(Long id) {
		logger.info(">>>>>> servico delete por id chamado");
		repository.deleteById(id);
	}

	@Override
	public Optional<Produto> altera(Produto produto) {
		logger.info(">>>>>> 1.servico altera produto chamado");
		Optional<Produto> umProduto = consultaPorId(produto.getId());

		if (umProduto.isPresent()) {
			Produto produtoModificado = new Produto(produto.getNome(), produto.getPreco());
			produtoModificado.setId(produto.getId());
			produtoModificado.setQtdEstoque(produto.getQtdEstoque());
			produtoModificado.setTipo(produto.getTipo());
			produtoModificado.setCnpj(produto.getCnpj());
			produtoModificado.setCor(produto.getCor());
			
			produtoModificado.obtemDataAtual(new DateTime());
			return Optional.ofNullable(repository.save(produtoModificado));
		} else {
			return Optional.empty();
		}
	}
}