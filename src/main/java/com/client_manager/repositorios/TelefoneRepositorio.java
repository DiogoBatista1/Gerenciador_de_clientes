package com.client_manager.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.client_manager.entidades.Cliente;

public interface TelefoneRepositorio extends JpaRepository<Cliente, Long> {

}
