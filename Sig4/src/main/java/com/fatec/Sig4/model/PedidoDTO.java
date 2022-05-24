package com.fatec.Sig4.model;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;
public class PedidoDTO {
    @CPF
    String cpf;
    @NotBlank
    String id;
    @NotBlank
    String quantidade;
    public PedidoDTO(String cpf, String id, String quantidade) {
    this.cpf = cpf;
    this.id = id;
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
    public String getId() {
    return id;
    }
    public void setId(String id) {
    this.id = id;
    }
    public String getQuantidade() {
    return quantidade;
    }
    public void setQuantidade(String quantidade) {
    this.quantidade = quantidade;
    }
    // equals e toString omitidos
   }