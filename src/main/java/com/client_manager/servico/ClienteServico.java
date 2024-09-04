package com.client_manager.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.client_manager.entidades.Cliente;
import com.client_manager.repositorios.ClienteRepositorio;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

@Service
public class ClienteServico {
	
	@Autowired
	private ClienteRepositorio clienteRepositorio;
	
	public List<Cliente> findAll() {
		return clienteRepositorio.findAll();
	}
	
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteRepositorio.findAll(pageable);
	}
	
	public Page<Cliente> findByNomeContainingIgnoreCase(String nome, Pageable pageable){
		return clienteRepositorio.findByNomeContainingIgnoreCase(nome, pageable);
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
