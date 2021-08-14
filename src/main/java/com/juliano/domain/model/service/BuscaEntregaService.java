package com.juliano.domain.model.service;

import org.springframework.stereotype.Service;

import com.juliano.domain.model.Entrega;
import com.juliano.domain.model.exception.EntidadeNaoEncontrada;
import com.juliano.domain.model.exception.NegocioException;
import com.juliano.domain.repository.EntregaRepository;

@Service
public class BuscaEntregaService {

	private EntregaRepository entregaRepository;
	
	public Entrega buscar(Long entregaId) {
		return entregaRepository.findById(entregaId)
				.orElseThrow(() -> new EntidadeNaoEncontrada("Entega não encontrada"));
	}

	public BuscaEntregaService(EntregaRepository entregaRepository) {
		super();
		this.entregaRepository = entregaRepository;
	}
	
}
