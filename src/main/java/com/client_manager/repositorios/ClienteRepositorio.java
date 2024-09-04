package com.client_manager.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.client_manager.entidades.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
	List<Cliente> findByNomeContainingIgnoreCase(String nome);
	Page<Cliente> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
