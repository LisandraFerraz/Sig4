package com.fatec.Sig4.ports;
import com.fatec.Sig4.model.PedidoProduto;

import org.springframework.data.jpa.repository.JpaRepository;
public interface PedidoProdutoRepository extends JpaRepository<PedidoProduto, Long> {
PedidoProduto findByProdutoId(Long codigo);
}
