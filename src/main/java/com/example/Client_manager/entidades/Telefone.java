package com.example.Client_manager.entidades;

import com.example.Client_manager.entidades.enums.TipoTelefone;

public class Telefone {
	
	private Long Id;
	
	private String numero;
	
	private TipoTelefone tipo;
	
	private Cliente cliente;

	public Long getId() {
		return Id;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
