package com.fatec.Sig4.model;

//equals e tostring omitidos. Cliado na aula de 05/04/2022
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.br.CNPJ;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "TIPO", nullable = false, length = 50)
	@NotBlank(message = "Tipo é requerido")
	private String tipo;

	@Column(name = "COR", nullable = false, length = 50)
	@NotBlank(message = "Cor é requerida")
	private String cor;
	@CNPJ
	@Column(unique = true) // nao funciona com @Valid tem que tratar na camada de persistencia
	private String cnpj;

	@Column(name = "NOME", nullable = false, length = 50)
	@NotBlank(message = "Nome é requerido")
	private String nome;

	@Column(name = "QTDESTOQUE", nullable = false, length = 4)
	@Min(value = 1, message = "Valor Minimo para estoque é 1.")
	@Max(value = 1000, message = "Valor Máximo para estoque é 1000.")
	private int qtdEstoque;

	@Column(name = "PRECO", nullable = false, length = 50)
	@NotNull(message = "Preço é requerido")
	private Double preco;

	@Column(name = "DATA", nullable = false, length = 10)
	@Pattern(regexp = "^(0?[1-9]|[12][0-9]|3[01])[\\/-](0?[1-9]|1[012])[\\/-]\\d{4}$", message = "A data de vencimento deve estar no formato dd/MM/YYYY")
	private String dataCadastro;

	@Column(name = "NOMEIMAGEM")
	private String nomeImagem;

	public String getNomeImagem() {
		return nomeImagem;
	}

	public void setNomeImagem(String nomeImagem) {
		this.nomeImagem = nomeImagem;
	}

	public Produto(String nome, Double preco, String tipo, String cor, String cnpj,String nomeImagem) {
		this.nome = nome;
		this.tipo = tipo;
		this.cor = cor;
		this.cnpj = cnpj;
		this.setPreco(preco);
		this.nomeImagem=nomeImagem;
	}

	public Produto() {

	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public int getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(int qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataAtual) {
		this.dataCadastro = dataAtual;
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

	public void obtemDataAtual(DateTime dataAtual) {
		DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/YYYY");
		this.dataCadastro = dataAtual.toString(fmt);
	}

}
