package com.helpdesk.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helpdesk.app.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
