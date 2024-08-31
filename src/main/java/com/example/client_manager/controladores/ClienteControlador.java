package com.example.client_manager.controladores;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.client_manager.conversor.ClienteConversor;
import com.example.client_manager.dto.ClienteDTO;
import com.example.client_manager.dto.EmailDTO;
import com.example.client_manager.dto.RedeSocialDTO;
import com.example.client_manager.dto.TelefoneDTO;
import com.example.client_manager.entidades.Cliente;
import com.example.client_manager.entidades.Email;
import com.example.client_manager.entidades.RedeSocial;
import com.example.client_manager.entidades.Telefone;
import com.example.client_manager.entidades.enums.TipoTelefone;
import com.example.client_manager.repositorios.ClienteRepositorio;
import com.example.client_manager.servico.ClienteServico;

@RestController
@RequestMapping("/clientes")
public class ClienteControlador {

	@Autowired
	public ClienteServico clienteServico;
	
	@Autowired
	private ClienteRepositorio clienteRepositorio;
	

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

	@GetMapping("/tipos-telefone")
	public ResponseEntity<List<String>> getTiposTelefone() {
		List<String> tiposTelefone = Arrays.stream(TipoTelefone.values()).map(Enum::name).collect(Collectors.toList());
		return ResponseEntity.ok(tiposTelefone);
	}

	@GetMapping("/clientes/pesquisar")
	public List<ClienteDTO> pesquisarCliente(@RequestParam String nome) {
		List<Cliente> clientes = clienteRepositorio.findByNomeContainingIgnoreCase(nome);
		return clientes.stream()
				.map(ClienteConversor::toDTO)
				.collect(Collectors.toList());
	}
	
	@PostMapping
	public ResponseEntity<ClienteDTO> createCliente(@RequestBody ClienteDTO clienteDTO) {
		Cliente cliente = ClienteConversor.toEntity(clienteDTO); 
		Cliente novoCliente = clienteServico.save(cliente);
		ClienteDTO clienteDTOResponse = ClienteConversor.toDTO(novoCliente); 
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteDTOResponse);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
		Cliente clienteExistente = clienteServico.findById(id);

		clienteExistente.setNome(clienteDTO.getNome());
		clienteExistente.setEndereco(clienteDTO.getEndereco());

		clienteExistente.getTelefones().clear();
		for (TelefoneDTO telefoneDTO : clienteDTO.getTelefones()) {
			Telefone telefone = new Telefone();
			telefone.setNumero(telefoneDTO.getNumero());
			telefone.setTipo(telefoneDTO.getTipo());
			telefone.setCliente(clienteExistente);
			clienteExistente.getTelefones().add(telefone);
		}

		// Atualiza emails
		List<Email> emailsExistentes = clienteExistente.getEmail();
		List<String> novosEmails = clienteDTO.getEmails().stream().map(EmailDTO::getEndereco)
				.collect(Collectors.toList());

		// atualiza ou adiciona novos emails
		for (EmailDTO emailDTO : clienteDTO.getEmails()) {
			Email emailExistente = emailsExistentes.stream()
					.filter(email -> email.getEndereco().equals(emailDTO.getEndereco())).findFirst().orElse(null);

			if (emailExistente != null) {
				emailExistente.setEndereco(emailDTO.getEndereco());
			} else {
				Email novoEmail = new Email();
				novoEmail.setEndereco(emailDTO.getEndereco());
				novoEmail.setCliente(clienteExistente);
				clienteExistente.getEmail().add(novoEmail);
			}
		}

		// Atualiza redes sociais
	    clienteExistente.getRedesSociais().clear();
	    for (RedeSocialDTO redeSocialDTO : clienteDTO.getRedesSociais()) {
	        RedeSocial redeSocial = new RedeSocial();
	        redeSocial.setNome(redeSocialDTO.getNome());
	        redeSocial.setUrl(redeSocialDTO.getUrl());
	        redeSocial.setTipo(redeSocialDTO.getTipo());
	        redeSocial.setCliente(clienteExistente);
	        clienteExistente.getRedesSociais().add(redeSocial);
	    }

		clienteExistente.getEmail().removeIf(email -> !novosEmails.contains(email.getEndereco()));

		Cliente clienteAtualizado = clienteServico.save(clienteExistente);
		ClienteDTO clienteDTOResponse = ClienteConversor.toDTO(clienteAtualizado);
		return ResponseEntity.ok(clienteDTOResponse);
	}

	@DeleteMapping("/{id}")
	public void deleteCliente(@PathVariable Long id) {
		clienteServico.delete(id);
	}
}
