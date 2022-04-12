package com.fatec.Sig4.ports;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatec.Sig4.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	Optional<Produto> findByID(String id);

	List<Produto> findAllByNomeIgnoreCaseContaining(String nome);
	
}
