package com.example.client_manager.conversor;

import java.util.List;
import java.util.stream.Collectors;

import com.example.client_manager.dto.ClienteDTO;
import com.example.client_manager.dto.EmailDTO;
import com.example.client_manager.dto.TelefoneDTO;
import com.example.client_manager.entidades.Cliente;
import com.example.client_manager.entidades.Email;
import com.example.client_manager.entidades.Telefone;

public class ClienteConversor {
	
	 // Converte um ClienteDTO para uma entidade Cliente
    public static Cliente toEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setEndereco(clienteDTO.getEndereco());

        for (TelefoneDTO telefoneDTO : clienteDTO.getTelefones()) {
            Telefone telefone = new Telefone();
            telefone.setNumero(telefoneDTO.getNumero());
            telefone.setTipo(telefoneDTO.getTipo());
            telefone.setCliente(cliente); // Associa o telefone ao cliente
            cliente.getTelefones().add(telefone);
        }

        for (EmailDTO emailDTO : clienteDTO.getEmails()) {
            Email email = new Email();
            email.setEndereco(emailDTO.getEndereco());
            email.setCliente(cliente); // Associa o email ao cliente
            cliente.getEmail().add(email);
        }

        return cliente;
    }
	
	public static ClienteDTO toDTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setEndereco(cliente.getEndereco());

        if (cliente.getTelefones() != null) {
            dto.setTelefones(cliente.getTelefones().stream()
                .map(t -> new TelefoneDTO(t.getId(), t.getNumero(), t.getTipo()))
                .collect(Collectors.toList()));
        }

        if (cliente.getEmail() != null) {
            dto.setEmails(cliente.getEmail().stream()
                .map(e -> new EmailDTO(e.getId(), e.getEndereco()))
                .collect(Collectors.toList()));
        }

        return dto;
    }
	
	public static List<ClienteDTO> toDTOList(List<Cliente> clientes) {
		return clientes.stream()
				.map(ClienteConversor::toDTO)
				.collect(Collectors.toList());
	}
}
