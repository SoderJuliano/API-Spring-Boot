package com.juliano.domain.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juliano.domain.model.Cliente;
import com.juliano.domain.model.exception.NegocioException;
import com.juliano.domain.repository.ClienteRepository;

@Service
public class CatalagoClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
				.stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		
		if(emailEmUso) {
			throw new NegocioException("Já existe um cliente cadastrado com este e-mail");
		}
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void excluir(Long clientId) {
		clienteRepository.deleteById(clientId);
	}

}
