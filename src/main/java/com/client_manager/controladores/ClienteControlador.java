package com.client_manager.controladores;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import com.client_manager.conversor.ClienteConversor;
import com.client_manager.dto.ClienteDTO;
import com.client_manager.dto.EmailDTO;
import com.client_manager.dto.PaginatedResponse;
import com.client_manager.dto.RedeSocialDTO;
import com.client_manager.dto.TelefoneDTO;
import com.client_manager.entidades.Cliente;
import com.client_manager.entidades.Email;
import com.client_manager.entidades.RedeSocial;
import com.client_manager.entidades.Telefone;
import com.client_manager.entidades.enums.TipoTelefone;
import com.client_manager.servico.ClienteServico;

@RestController
@RequestMapping("/clientes")
public class ClienteControlador {

    @Autowired
    public ClienteServico clienteServico;

    @GetMapping
    public ResponseEntity<?> getClientes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String searchTerm) {

        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Cliente> clientesPage;

            if (searchTerm.isEmpty()) {
                clientesPage = clienteServico.findAll(pageable);
            } else {
                clientesPage = clienteServico.findByNomeContainingIgnoreCase(searchTerm, pageable);
            }

            List<ClienteDTO> clientesDTO = clientesPage.getContent()
                    .stream()
                    .map(ClienteConversor::toDTO)
                    .collect(Collectors.toList());

            // Preparar resposta com informações de paginação
            return ResponseEntity.ok(new PaginatedResponse<>(
                    clientesDTO,
                    clientesPage.getNumber(),
                    clientesPage.getSize(),
                    clientesPage.getTotalElements(),
                    clientesPage.getTotalPages(),
                    clientesPage.isLast()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao buscar clientes: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Long id) {
        Cliente cliente = clienteServico.findById(id);
        ClienteDTO clienteDTO = ClienteConversor.toDTO(cliente);
        return ResponseEntity.ok(clienteDTO);
    }

    @GetMapping("/tipos-telefone")
    public ResponseEntity<List<String>> getTiposTelefone() {
        List<String> tiposTelefone = Arrays.stream(TipoTelefone.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tiposTelefone);
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

        // Atualizar telefones
        clienteExistente.getTelefones().clear();
        for (TelefoneDTO telefoneDTO : clienteDTO.getTelefones()) {
            Telefone telefone = new Telefone();
            telefone.setNumero(telefoneDTO.getNumero());
            telefone.setTipo(telefoneDTO.getTipo());
            telefone.setCliente(clienteExistente);
            clienteExistente.getTelefones().add(telefone);
        }

        // Atualizar emails
        List<Email> emailsExistentes = clienteExistente.getEmail();
        List<String> novosEmails = clienteDTO.getEmails().stream()
                .map(EmailDTO::getEndereco)
                .collect(Collectors.toList());

        // Atualiza ou adiciona novos emails
        for (EmailDTO emailDTO : clienteDTO.getEmails()) {
            Email emailExistente = emailsExistentes.stream()
                    .filter(email -> email.getEndereco().equalsIgnoreCase(emailDTO.getEndereco()))
                    .findFirst()
                    .orElse(null);

            if (emailExistente != null) {
                emailExistente.setEndereco(emailDTO.getEndereco());
            } else {
                Email novoEmail = new Email();
                novoEmail.setEndereco(emailDTO.getEndereco());
                novoEmail.setCliente(clienteExistente);
                clienteExistente.getEmail().add(novoEmail);
            }
        }

        // Remover emails que não estão mais presentes
        clienteExistente.getEmail().removeIf(email -> !novosEmails.contains(email.getEndereco()));

        // Atualizar redes sociais
        clienteExistente.getRedesSociais().clear();
        for (RedeSocialDTO redeSocialDTO : clienteDTO.getRedesSociais()) {
            RedeSocial redeSocial = new RedeSocial();
            redeSocial.setNome(redeSocialDTO.getNome());
            redeSocial.setUrl(redeSocialDTO.getUrl());
            redeSocial.setTipo(redeSocialDTO.getTipo());
            redeSocial.setCliente(clienteExistente);
            clienteExistente.getRedesSociais().add(redeSocial);
        }

        Cliente clienteAtualizado = clienteServico.save(clienteExistente);
        ClienteDTO clienteDTOResponse = ClienteConversor.toDTO(clienteAtualizado);
        return ResponseEntity.ok(clienteDTOResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
        try {
            clienteServico.delete(id);
            return ResponseEntity.ok("Cliente deletado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao deletar cliente: " + e.getMessage());
        }
    }
}
