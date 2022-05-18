package com.fatec.Sig4.adapters;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.fatec.Sig4.model.ItemDePedido;
import com.fatec.Sig4.model.Pedido;
import com.fatec.Sig4.model.PedidoDTO;
import com.fatec.Sig4.model.Produto;
import com.fatec.Sig4.ports.MantemPedido;

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

@Controller
@RequestMapping(path = "/scv")
public class GUIPedidoController {
    Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    MantemPedido mantemPedido;

    @GetMapping("/pedido")
    public ModelAndView cadastrarPedido(PedidoDTO umPedido) {
        logger.info(">>>>>> 1. controller pagina cadastrar pedido chamada ");
        ModelAndView mv = new ModelAndView("cadastrarPedido");
        mv.addObject("umPedido", new PedidoDTO());
        return mv;
    }

    @GetMapping("/pedidos")
    public ModelAndView consultaPedidos() {
        ModelAndView mv = new ModelAndView("consultarPedido");
        logger.info(">>>>>> 1. controller chamou a api nativa de consulta de pedidos");
        mv.addObject("pedidos", mantemPedido.consultaTodos());
        return mv;
    }

    @PostMapping("/pedidos")
    public ModelAndView save(@Valid PedidoDTO umPedido, BindingResult result) {
        ModelAndView mv = new ModelAndView("consultarPedido");
        Pedido pedido = new Pedido();
        logger.info(">>>>>> 1. controller save iniciado ");
        if (result.hasErrors()) {
            logger.info(">>>>>> 1. erro no pedido dto ");
            mv.setViewName("cadastrarPedido");
            // mv.addObject("umPedido", new PedidoDTO());
            mv.addObject("umPedido", umPedido);
            mv.addObject("message", "Dados inválidos");
        } else {
            logger.info(">>>>>> 1. controller save dados validos");
            pedido.setCpf(umPedido.getCpf());
            ItemDePedido item = new ItemDePedido();
            Produto produto = new Produto();
            produto.setProdutoId(Long.parseLong(umPedido.getProdutoId()));
            item.setProduto(produto);
            item.setQuantidade(Integer.parseInt(umPedido.getQuantidade()));
            List<ItemDePedido> itens = new ArrayList<ItemDePedido>();
            itens.add(item);
            pedido.setItens(itens);
            logger.info(">>>>>> 1. carregou os itens ");
            mantemPedido.cadastrarPedido(pedido);
            mv.addObject("pedidos", mantemPedido.consultaTodos());
        }
        return mv;
    }

    @GetMapping("/pedidos/id/{id}")
    public ModelAndView excluirPedido(@PathVariable("id") Long id) {
        logger.info(">>>>>> 1. servico de exclusao chamado ");
        mantemPedido.excluiPedido(id);
        ModelAndView modelAndView = new ModelAndView("consultarPedido");
        modelAndView.addObject("pedidos", mantemPedido.consultaTodos());
        return modelAndView;
    }
}