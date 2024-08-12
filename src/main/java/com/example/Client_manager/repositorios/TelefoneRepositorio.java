package com.example.Client_manager.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Client_manager.entidades.Cliente;

public interface TelefoneRepositorio extends JpaRepository<Cliente, Long> {

}
