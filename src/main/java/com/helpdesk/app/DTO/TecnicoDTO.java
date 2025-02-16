package com.helpdesk.app.DTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.helpdesk.app.domain.Tecnico;
import com.helpdesk.app.domain.enums.Perfil;

public class TecnicoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	protected Integer id;
	@NotNull(message = "O campo NOME é obrigatorio")
	protected String nome;
	@CPF
	@NotNull(message = "O campo CPF é obrigatorio")
	protected String cpf;
	@NotNull(message = "O campo EMAIL é obrigatorio")
	@Email
	protected String email;
	@NotNull(message = "O campo SENHA é obrigatorio")
	protected String senha;
	protected Set<Integer> perfis = new HashSet<>();
	
	@JsonFormat(pattern = "dd/M/yyyy")
	protected LocalDate dataCriacao = LocalDate.now();

	public TecnicoDTO() {
		super();
		addPerfil(Perfil.CLIENTE);
	}

	public TecnicoDTO(Tecnico obj) {
		super();
		
		addPerfil(Perfil.CLIENTE);
		
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
		this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.dataCriacao = obj.getDataCriacao();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		this.perfis.add(perfil.getCodigo());
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
}
