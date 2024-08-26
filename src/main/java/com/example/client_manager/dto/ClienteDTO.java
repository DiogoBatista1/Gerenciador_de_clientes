package com.example.client_manager.dto;

import java.util.List;

public class ClienteDTO {
	private Long id;
	private String nome;
	private String endereco;
	private List<TelefoneDTO> telefones;
	private List<EmailDTO> emails;
	private List<RedeSocialDTO> redesSociais;

	public ClienteDTO() {
	}

	public ClienteDTO(Long id, String nome, String endereco, List<TelefoneDTO> telefones, List<EmailDTO> emails) {
		super();
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.telefones = telefones;
		this.emails = emails;
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<TelefoneDTO> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<TelefoneDTO> telefones) {
		this.telefones = telefones;
	}

	public List<EmailDTO> getEmails() {
		return emails;
	}

	public void setEmails(List<EmailDTO> emails) {
		this.emails = emails;
	}

	public List<RedeSocialDTO> getRedesSociais() {
		return redesSociais;
	}

	public void setRedesSociais(List<RedeSocialDTO> redesSociais) {
		this.redesSociais = redesSociais;
	}
}
