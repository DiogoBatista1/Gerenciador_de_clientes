package com.example.client_manager.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.client_manager.entidades.Cliente;
import com.example.client_manager.servico.ClienteServico;

public class ClienteControlador {
	
	@Autowired
	public ClienteServico clienteServico;
	
	@GetMapping
	public List<Cliente> getAllClientes() {
		return clienteServico.findAll();
	}
	
	@GetMapping("/{id}")
	public Cliente getClienteById(@PathVariable Long id) {
		return clienteServico.findById(id);
	}
	
	@PostMapping
	public Cliente createCliente(@RequestBody Cliente cliente) {
		return clienteServico.save(cliente);
	}
	
	public Cliente updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
		Cliente clienteExistente = clienteServico.findById(id);
		clienteExistente.setNome(cliente.getNome());
		clienteExistente.setEndereco(cliente.getEndereco());
		
		return clienteServico.save(clienteExistente);
	}
	
	public void deleteCliente(@PathVariable Long id) {
		clienteServico.delete(id);
	}
}
