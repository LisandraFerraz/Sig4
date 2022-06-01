package com.fatec.Sig4.ports;

import java.util.List;
import java.util.Optional;

import com.fatec.Sig4.model.Produto;

public interface MantemProduto {
	List<Produto> consultaTodos();
	Optional<Produto> consultaPorId(Long idProduto);
	Optional<Produto> save(Produto produto);
	void delete(Long idProduto);
	Optional<Produto> altera(Produto produto);
}
