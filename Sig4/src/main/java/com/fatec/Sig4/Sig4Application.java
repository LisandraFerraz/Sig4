package com.fatec.Sig4;

import java.util.Arrays;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner; 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.fatec.Sig4.model.Cliente;
import com.fatec.Sig4.model.ItemDePedido;
import com.fatec.Sig4.model.Pedido;
import com.fatec.Sig4.model.PedidoProduto;
import com.fatec.Sig4.ports.MantemCliente;
import com.fatec.Sig4.ports.MantemPedido;
import com.fatec.Sig4.ports.PedidoProdutoRepository;

@SpringBootApplication
public class Sig4Application implements CommandLineRunner {
	@Autowired
	private PedidoProdutoRepository produtoRepository;
	@Autowired
	private MantemCliente mantemCliente;
	@Autowired
	private MantemPedido servico;
	Logger logger = LogManager.getLogger(this.getClass());
	public static void main(String[] args) {
	SpringApplication.run(Sig4Application.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
	// ******************************************************************************************
	// Cadastrar tres clientes na base - a cliente Claudia não comprou nada
	// ******************************************************************************************
	Cliente umCliente = new Cliente("Andrade", "25/05/1960", "M", "99504993052", "04280130", "1234");
	mantemCliente.save(umCliente);
	umCliente = new Cliente("Silva", "18/03/1964", "M", "43011831084", "08545160", "1234");
	mantemCliente.save(umCliente);
	umCliente = new Cliente("Claudia", "11/05/1974", "F", "85765535380", "08545160", "1234");
	mantemCliente.save(umCliente);
	// ******************************************************************************************
	// Cadastrar tres produtos na base de dados
	// ******************************************************************************************
	PedidoProduto produto1 = new PedidoProduto(1L,"parafuso", 10, 30); // descricao, custo e quantidade no estoque
	PedidoProduto produto2 = new PedidoProduto(2L, "tijolo", 15, 60);
	PedidoProduto produto3 = new PedidoProduto(3L, "bucha", 5, 50);
	produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));
	// *******************************************************************************************
	// Cadastrar Pedido1 - entrada de dados de pedido - o cliente deve estar previamente cadastrado
	// informações sobre o nome do cliente, endereco sao obtidos com base no cpf do cliente
	// *******************************************************************************************
	Pedido pedido1 = new Pedido("43011831084");
	// *******************************************************************************************
	// Detalhes do pedido - o produto deve estar cadastrado este cliente comprou 2 itens parafuso e bucha
	// *******************************************************************************************
	Optional<PedidoProduto> umProduto = produtoRepository.findById(1L);
	PedidoProduto produtoComprado1 = umProduto.get();
	umProduto = produtoRepository.findById(3L);
	PedidoProduto produtoComprado2 = umProduto.get();
	ItemDePedido ip1 = new ItemDePedido(produtoComprado1, 20); // quantidade comprada
	ItemDePedido ip2 = new ItemDePedido(produtoComprado2, 10); // quantidade comprada
	// *******************************************************************************************
	// adiciona os itens comprados no pedido e salva
	// *******************************************************************************************
	pedido1.getItens().addAll(Arrays.asList(ip1, ip2));
	servico.cadastrarPedido(pedido1);
	// *******************************************************************************************
	// Cadastrar Pedido 2 - entrada de dados este cliente comprou somente um item tijolo
	// *******************************************************************************************
	Pedido pedido2 = new Pedido("43011831084");
	umProduto = produtoRepository.findById(2L);
	produtoComprado1 = umProduto.get();
	ip1 = new ItemDePedido(produtoComprado1, 20); // quantidade comprada
	pedido2.getItens().addAll(Arrays.asList(ip1));
	servico.cadastrarPedido(pedido2);
	// *******************************************************************************************
	// Cadastrar Pedido 3 - entrada de dados este cliente comprou somente bucha
	// *******************************************************************************************
	Pedido pedido3 = new Pedido("99504993052");
	umProduto = produtoRepository.findById(3L);
	produtoComprado1 = umProduto.get();
	ip1 = new ItemDePedido(produtoComprado1, 12); // quantidade comprada
	pedido3.getItens().addAll(Arrays.asList(ip1));
	servico.cadastrarPedido(pedido3);
	}
   }