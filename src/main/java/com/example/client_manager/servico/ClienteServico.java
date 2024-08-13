package com.example.client_manager.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.services.exceptions.ResourceNotFoundException;
import com.example.client_manager.entidades.Cliente;
import com.example.client_manager.repositorios.ClienteRepositorio;

@Service
public class ClienteServico {
	
	@Autowired
	private ClienteRepositorio clienteRepositorio;
	
	public List<Cliente> findALL() {
		return clienteRepositorio.findAll();
	}
	
	public Cliente findById(Long id) {
		return clienteRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado!"));
	}
	
	public Cliente save(Cliente cliente) {
		return clienteRepositorio.save(cliente);
	}
	
	public void delete(Long id) {
		clienteRepositorio.deleteById(id);
	}
}
