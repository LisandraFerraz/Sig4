package com.fatec.Sig4.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //manipula - lazy loaded properties
@Entity
public class PedidoProduto {
 @Id
 private Long id;
 private String descricao;
 private double preco;
 private int quantidade;

public PedidoProduto(Long id, String descricao, double preco, int quantidade) {
 this.id = id;
 this.descricao = descricao;
 this.quantidade = quantidade;
 this.preco = preco;
 }
 @Deprecated
 public PedidoProduto() {
 }

 public Long getId() {
 return id;
 }
 public void setId(Long id) {
 this.id = id;
 }
 public String getDescricao() {
 return descricao;
 }
 public void setDescricao(String descricao) {
 this.descricao = descricao;
 }
 public int getQuantidade() {
 return quantidade;
 }
 public void setQuantidade(int quantidade) {
 this.quantidade = quantidade;
 }
 public double getPreco() {
 return preco;
 }
 public void setPreco(double preco) {
 this.preco = preco;
 }
} 