package com.example.client_manager.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.client_manager.entidades.Cliente;

public interface EmailRepositorio extends JpaRepository<Cliente, Long> {

}
