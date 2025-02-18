package com.helpdesk.app.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.helpdesk.app.DTO.ClienteDTO;
import com.helpdesk.app.domain.Cliente;
import com.helpdesk.app.domain.Pessoa;
import com.helpdesk.app.exception.DataIntegrityViolationException;
import com.helpdesk.app.exception.ObjectNotFoundException;
import com.helpdesk.app.repositories.ClienteRepository;
import com.helpdesk.app.repositories.PessoaRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontado!"));
	}
	
	public List<Cliente> findAll() {
		return repository.findAll();
	}
	
	public Cliente create(ClienteDTO obj) {
		obj.setId(null);
		obj.setSenha(encoder.encode(obj.getSenha()));
		validaPorCpjEEmail(obj);
		Cliente newObj = new Cliente(obj);
		return repository.save(newObj);
	}
	
	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		objDTO.setId(id);
		Cliente oldObj = findById(id);
		validaPorCpjEEmail(objDTO);
		oldObj = new Cliente(objDTO);
		return repository.save(oldObj);
	}
	
	public void delete (Integer id) {
		Cliente obj = findById(id);
		if(obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Cliente possui chamados e não pode ser deletado!");
		}
		repository.deleteById(id);
	}

	private void validaPorCpjEEmail(ClienteDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado!");
		}
		
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("Email já cadastrado!");
		}
	}
}
