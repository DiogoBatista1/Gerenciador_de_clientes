package com.client_manager.dto;

public class EmailDTO {
	private Long id;
	private String endereco;
	 
	public EmailDTO() {
	}
	
	public EmailDTO(Long id, String endereco) {
		super();
		this.id = id;
		this.endereco = endereco;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
}
