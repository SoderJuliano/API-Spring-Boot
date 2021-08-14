package com.juliano.domain.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juliano.domain.model.Entrega;
import com.juliano.domain.model.Ocorrencia;
import com.juliano.domain.model.exception.NegocioException;
import com.juliano.domain.repository.EntregaRepository;

@Service
public class RegistroOcorrenciaService {

	private BuscaEntregaService buscaEntregaService;
	
	@Transactional
	public Ocorrencia registrar(Long entregaId, String descricao) {
			Entrega entrega = buscaEntregaService.buscar(entregaId);
			
			return entrega.adicionarOcorrencia(descricao);
	}

	public RegistroOcorrenciaService(BuscaEntregaService buscaEntregaService) {
		super();
		this.buscaEntregaService = buscaEntregaService;
	}

	
	
}
