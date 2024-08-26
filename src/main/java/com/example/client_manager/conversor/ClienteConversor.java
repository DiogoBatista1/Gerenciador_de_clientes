package com.example.client_manager.conversor;

import java.util.List;
import java.util.stream.Collectors;

import com.example.client_manager.dto.ClienteDTO;
import com.example.client_manager.dto.EmailDTO;
import com.example.client_manager.dto.RedeSocialDTO;
import com.example.client_manager.dto.TelefoneDTO;
import com.example.client_manager.entidades.Cliente;
import com.example.client_manager.entidades.Email;
import com.example.client_manager.entidades.RedeSocial;
import com.example.client_manager.entidades.Telefone;

public class ClienteConversor {
	
    public static Cliente toEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setEndereco(clienteDTO.getEndereco());

        for (TelefoneDTO telefoneDTO : clienteDTO.getTelefones()) {
            Telefone telefone = new Telefone();
            telefone.setNumero(telefoneDTO.getNumero());
            telefone.setTipo(telefoneDTO.getTipo());
            telefone.setCliente(cliente); 
            cliente.getTelefones().add(telefone);
        }

        for (EmailDTO emailDTO : clienteDTO.getEmails()) {
            Email email = new Email();
            email.setEndereco(emailDTO.getEndereco());
            email.setCliente(cliente); 
            cliente.getEmail().add(email);
        }
        
        for (RedeSocialDTO redeSocialDTO : clienteDTO.getRedesSociais()) {
        	RedeSocial redeSocial = new RedeSocial();
        	redeSocial.setNome(redeSocialDTO.getNome());
        	redeSocial.setUrl(redeSocialDTO.getUrl());
        	redeSocial.setTipo(redeSocialDTO.getTipo());
        	redeSocial.setCliente(cliente);
        	cliente.getRedesSociais().add(redeSocial);
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
        
        if (cliente.getRedesSociais() != null) {
            dto.setRedesSociais(cliente.getRedesSociais().stream()
                .map(rede -> {
                    RedeSocialDTO redeDTO = new RedeSocialDTO();
                    redeDTO.setId(rede.getId());
                    redeDTO.setNome(rede.getNome());
                    redeDTO.setUrl(rede.getUrl());
                    redeDTO.setTipo(rede.getTipo());
                    return redeDTO;
                })
                .collect(Collectors.toList()));
        }

        return dto;
    }
	
	public static List<ClienteDTO> toDTOList(List<Cliente> clientes) {
		return clientes.stream()
				.map(ClienteConversor::toDTO)
				.collect(Collectors.toList());
	}
	
	public RedeSocialDTO toRedeSocialDTO(RedeSocial redeSocial) {
		RedeSocialDTO dto = new RedeSocialDTO();
		dto.setId(redeSocial.getId());
		dto.setNome(redeSocial.getNome());
		dto.setUrl(redeSocial.getUrl());
		dto.setTipo(redeSocial.getTipo());
		return dto;
	}
	
	public RedeSocial toRedeSocialEntity(RedeSocialDTO dto) {
		RedeSocial redeSocial = new RedeSocial();
		redeSocial.setId(dto.getId());
		redeSocial.setNome(dto.getNome());
		redeSocial.setUrl(dto.getUrl());
		redeSocial.setTipo(dto.getTipo());
		return redeSocial;
	}
}
