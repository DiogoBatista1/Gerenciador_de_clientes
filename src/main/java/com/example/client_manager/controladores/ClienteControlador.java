package com.example.client_manager.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.client_manager.conversor.ClienteConversor;
import com.example.client_manager.dto.ClienteDTO;
import com.example.client_manager.entidades.Cliente;
import com.example.client_manager.repositorios.ClienteRepositorio;
import com.example.client_manager.servico.ClienteServico;

@RestController
@RequestMapping("/clientes")
public class ClienteControlador {

	@Autowired
	public ClienteServico clienteServico;

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> getAllClientes() {
		List<Cliente> clientes = clienteServico.findAll();
		List<ClienteDTO> clientesDTO = ClienteConversor.toDTOList(clientes);
		return ResponseEntity.ok(clientesDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Long id) {
		Cliente cliente = clienteServico.findById(id);
		ClienteDTO clienteDTO = ClienteConversor.toDTO(cliente);
		return ResponseEntity.ok(clienteDTO);
	}

	@PostMapping
	public ResponseEntity<ClienteDTO> createCliente(@RequestBody ClienteDTO clienteDTO) {
	    Cliente cliente = ClienteConversor.toEntity(clienteDTO); // Converte DTO para entidade
	    Cliente novoCliente = clienteServico.save(cliente);
	    ClienteDTO clienteDTOResponse = ClienteConversor.toDTO(novoCliente); // Converte entidade para DTO
	    return ResponseEntity.status(HttpStatus.CREATED).body(clienteDTOResponse);
	}


	@PutMapping("/{id}")
	public Cliente updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
		Cliente clienteExistente = clienteServico.findById(id);
		clienteExistente.setNome(cliente.getNome());
		clienteExistente.setEndereco(cliente.getEndereco());

		return clienteServico.save(clienteExistente);
	}

	@DeleteMapping("/{id}")
	public void deleteCliente(@PathVariable Long id) {
		clienteServico.delete(id);
	}
}
