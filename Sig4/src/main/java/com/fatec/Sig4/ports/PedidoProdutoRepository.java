package com.fatec.Sig4.ports;
import java.util.Optional;

import com.fatec.Sig4.model.PedidoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PedidoProdutoRepository extends JpaRepository<PedidoProduto, Long> {
Optional<PedidoProduto> findById(Long codigo);
}