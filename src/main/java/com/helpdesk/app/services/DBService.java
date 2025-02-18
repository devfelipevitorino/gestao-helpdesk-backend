package com.helpdesk.app.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.helpdesk.app.domain.Tecnico;
import com.helpdesk.app.domain.enums.Perfil;
import com.helpdesk.app.repositories.TecnicoRepository;



@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public void instaciaDB() {
		
		Tecnico t1 = new Tecnico(null, "admin", "77426482022", "admin@gmail.com", encoder.encode("admin")); t1.addPerfil(Perfil.ADMIN);
		
		tecnicoRepository.saveAll(Arrays.asList(t1));
		
	}
	
}
