package com.fatec.Sig4.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@NotBlank(message = "Nome é requerido")
	@Column(name = "NOME", nullable = false, length = 50)
	private String nome;
	@Column(name = "DATA", nullable = false, length = 10)
	@Pattern(regexp = "^(0?[1-9]|[12][0-9]|3[01])[\\/-](0?[1-9]|1[012])[\\/-]\\d{4}$", message = "A data de vencimento deve estar no formato dd/MM/YYYY")
	// https://www.regular-expressions.info/
	private String dataNascimento;
	private String dataCadastro;
	@Column(name = "SEXO", nullable = false, length = 1)
	private String sexo;
	@CPF
	@Column(unique = true) // nao funciona com @Valid tem que tratar na camada de persistencia
	private String cpf;
	@Column(name = "CEP", nullable = false, length = 9)
	@NotBlank(message = "O CEP é obritatório.")
	private String cep;
	@Column(name = "ENDERECO", nullable = false, length = 50)
	@NotBlank(message = "O endereço deve ser informado")
	private String endereco;
	@Column(name = "COMPLEMENTO", nullable = false, length = 50)
	@NotBlank(message = "O complemento deve ser informado")
	private String complemento;

	public Cliente(String nome, String dataNascimento, String sexo, String cpf, String cep, String complemento) {
		this.nome = nome;
		setDataNascimento(dataNascimento);
		this.sexo = sexo;
		this.cpf = cpf;
		this.cep = cep;
		this.complemento = complemento;
	}

	@Deprecated
	public Cliente() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataAtual) {
		this.dataCadastro = dataAtual;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		if (validaData(dataNascimento) == true) {
			this.dataNascimento = dataNascimento;
		} else {
			throw new IllegalArgumentException("Data invalida");
		}
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
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

	// equals e tostring omitidos

}
