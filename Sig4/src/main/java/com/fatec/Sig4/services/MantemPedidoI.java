package com.fatec.Sig4.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.fatec.Sig4.model.ItemDePedido;
import com.fatec.Sig4.model.Pedido;
import com.fatec.Sig4.ports.ItemDePedidoRepository;
import com.fatec.Sig4.ports.MantemCliente;
import com.fatec.Sig4.ports.MantemPedido;
import com.fatec.Sig4.ports.PedidoRepository;
import com.fatec.Sig4.ports.ProdutoRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MantemPedidoI implements MantemPedido {
    Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ItemDePedidoRepository itemRepository;
    @Autowired
    private MantemCliente mantemCliente;

    public Optional<Pedido> buscaPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public List<Pedido> buscaPorCpf(String cpf) {
        return pedidoRepository.findByCpf(cpf);
    }

    @Transactional
    public Pedido save(Pedido pedido) {
        logger.info(">>>>>> servico save chamado para o pedido - " + pedido.getCpf());
        DateTime dataAtual = new DateTime();
        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/YYYY");
        pedido.setDataEmissao(dataAtual.toString(fmt));
        Pedido umPedido = pedidoRepository.save(pedido);
        logger.info(">>>>>> cabecalho do pedido salvo no repositorio ");
        for (ItemDePedido item : pedido.getItens()) {
            item.setProduto(produtoRepository.getById(item.getProduto().getProdutoId()));
            item.setQuantidade(item.getQuantidade());
        }
        itemRepository.saveAll(pedido.getItens());
        logger.info(">>>>>> item do pedido salvo no repositorio ");
        return umPedido;
    }

    public boolean validaData(String data) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false); //
        try {
            df.parse(data); // data válida (exemplo 30 fev - 31 nov)
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }

    @Override
    public List<Pedido> consultaTodos() {
        return pedidoRepository.findAll();
    }

    @Transactional
    public void excluiPedido(Long id) {
        pedidoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Pedido cadastrarPedido(Pedido pedido) {
        logger.info(">>>>>> servico cadastrar pedido ");
        try {
            if (isClienteCadastrado(pedido.getCpf())) {
                logger.info(">>>>>> servico cadastrar pedido - cliente cadastrado ");
                return save(pedido);
            } else {
                logger.info(">>>>>> servico cadastrar pedido - cliente invalido ");
                return null;
            }
        } catch (Exception e) {
            logger.info(">>>>>> servico cadastrar pedido - erro nao esperado contate o administrador");
            return null;
        }
    }

    /**
     * Verifica se o cpf esta cadastrado na base
     * retorna true se estiver cadastrado
     */
    public boolean isClienteCadastrado(String cpf) {
        return mantemCliente.consultaPorCpf(cpf).isPresent();
    }
}