package com.helpdesk.app.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpdesk.app.domain.Chamado;
import com.helpdesk.app.domain.Cliente;
import com.helpdesk.app.domain.Tecnico;
import com.helpdesk.app.domain.enums.Perfil;
import com.helpdesk.app.domain.enums.Prioridade;
import com.helpdesk.app.domain.enums.Status;
import com.helpdesk.app.repositories.ChamadoRepository;
import com.helpdesk.app.repositories.ClienteRepository;
import com.helpdesk.app.repositories.TecnicoRepository;



@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	public void instaciaDB() {
		
		Tecnico t1 = new Tecnico(null, "Tecnico 1", "13017727475", "felipe@gmail.com", "123"); t1.addPerfil(Perfil.ADMIN);
		
		Cliente c1 = new Cliente(null, "Cliente 1", "13017727474", "Vitorino@gmail.com", "123");
		
		Chamado ch1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Titulo", "Observações", t1, c1);
		
		tecnicoRepository.saveAll(Arrays.asList(t1));
		
		clienteRepository.saveAll(Arrays.asList(c1));
		
		chamadoRepository.saveAll(Arrays.asList(ch1));
		
	}
	
}
