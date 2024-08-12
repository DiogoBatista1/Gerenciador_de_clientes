package com.example.Client_manager.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Client_manager.entidades.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

}
