package com.helpdesk.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helpdesk.app.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{

}
