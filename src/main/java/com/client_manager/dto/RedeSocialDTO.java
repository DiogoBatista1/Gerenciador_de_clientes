package com.client_manager.dto;

import com.client_manager.entidades.enums.TipoRedeSocial;

public class RedeSocialDTO {
	private Long id;
	private String nome;
	private String url;
	private TipoRedeSocial tipo;
	
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public TipoRedeSocial getTipo() {
		return tipo;
	}
	public void setTipo(TipoRedeSocial tipo) {
		this.tipo = tipo;
	}
}
