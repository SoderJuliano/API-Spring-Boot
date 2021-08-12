package com.juliano.domain.model.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juliano.domain.model.Cliente;
import com.juliano.domain.model.Entrega;
import com.juliano.domain.model.StatusEntrega;
import com.juliano.domain.model.exception.NegocioException;
import com.juliano.domain.repository.ClienteRepository;
import com.juliano.domain.repository.EntregaRepository;

@Service
public class SolicitacaoEntregaService {

	private ClienteRepository clienteRepository;
	private EntregaRepository entregaRepository;

	


	public SolicitacaoEntregaService(ClienteRepository clienteRepository, EntregaRepository entregaRepository) {
		super();
		this.clienteRepository = clienteRepository;
		this.entregaRepository = entregaRepository;
	}




	@Transactional
	public Entrega solicitar(Entrega entrega) {
		Cliente cliente = clienteRepository.findById(entrega.getCliente().getId())
		.orElseThrow(()-> new NegocioException("Cliente não encontrado"));
		
		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(LocalDateTime.now());
		return entregaRepository.save(entrega);
	}
	
}
