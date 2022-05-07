package com.fatec.Sig4.model;


//equals e tostring omitidos. Cliado na aula de 05/04/2022
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message = "Nome é requerido")
	private String nome;
	@Min(value=1, message = "Valor Minimo para estoque é 1.")
	@Max(value=1000,message = "Valor Máximo para estoque é 1000.")
	private int qtdEstoque;
	@NotNull(message = "Preço é requerido")
	private Double preco;
	@Pattern(regexp = "^(0?[1-9]|[12][0-9]|3[01])[\\/-](0?[1-9]|1[012])[\\/-]\\d{4}$",
			message = "A data de vencimento deve estar no formato dd/MM/YYYY")
	private String dataCadastro;
	
	
	public Produto(String nome, Double preco) {
		this.nome = nome;
		this.setPreco(preco);
	}

	public Produto() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque( int qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	// equals e tostring omitidos. Cliado na aula de 05/04/2022

}

