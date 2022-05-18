package com.fatec.Sig4.model;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;
public class PedidoDTO {
 @CPF
 String cpf;
 @NotBlank
 String produtoId;
 @NotBlank
 String quantidade;
 public PedidoDTO(String cpf, String produtoId, String quantidade) {
 this.cpf = cpf;
 this.produtoId = produtoId;
 this.quantidade = quantidade;
 }
 public PedidoDTO() {
 }
 public String getCpf() {
 return cpf;
 }
 public void setCpf(String cpf) {
 this.cpf = cpf;
 }
 public String getProdutoId() {
 return produtoId;
 }
 public void setProdutoId(String produtoId) {
 this.produtoId = produtoId;
 }
 public String getQuantidade() {
 return quantidade;
 }
 public void setQuantidade(String quantidade) {
 this.quantidade = quantidade;
 }
 // equals e toString omitidos
}