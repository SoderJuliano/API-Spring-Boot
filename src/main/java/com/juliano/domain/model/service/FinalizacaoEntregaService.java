package com.juliano.domain.model.service;

import org.springframework.stereotype.Service;

import com.juliano.domain.model.Entrega;
import com.juliano.domain.repository.EntregaRepository;

@Service
public class FinalizacaoEntregaService {

	
	private EntregaRepository estregaRepository;
	private BuscaEntregaService buscaEntregaService;
	
	public void finalizar(Long entregaId) {
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		
		entrega.finalizar();
		
		estregaRepository.save(entrega);
	}

	public FinalizacaoEntregaService(EntregaRepository estregaRepository, BuscaEntregaService buscaEntregaService) {
		super();
		this.estregaRepository = estregaRepository;
		this.buscaEntregaService = buscaEntregaService;
	}
	
	
}
