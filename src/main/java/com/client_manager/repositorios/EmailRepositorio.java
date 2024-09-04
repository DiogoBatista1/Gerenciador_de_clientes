package com.client_manager.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.client_manager.entidades.Cliente;

public interface EmailRepositorio extends JpaRepository<Cliente, Long> {

}
