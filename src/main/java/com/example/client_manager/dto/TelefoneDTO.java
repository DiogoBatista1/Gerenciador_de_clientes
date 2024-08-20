package com.example.client_manager.dto;

import com.example.client_manager.entidades.enums.TipoTelefone;

public class TelefoneDTO {
	private Long id;
	private String numero;
	private TipoTelefone tipo;

	public TelefoneDTO() {
	}
	
	public TelefoneDTO(Long id, String numero, TipoTelefone tipo) {
		super();
		this.id = id;
		this.numero = numero;
		this.tipo = tipo;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public TipoTelefone getTipo() {
		return tipo;
	}
	public void setTipo(TipoTelefone tipo) {
		this.tipo = tipo;
	}
}
