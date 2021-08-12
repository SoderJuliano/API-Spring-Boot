package com.juliano.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.juliano.domain.model.Entrega;
import com.juliano.domain.model.service.SolicitacaoEntregaService;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private SolicitacaoEntregaService solicitacaoEntregaService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Entrega solicitar(@RequestBody Entrega entregra) {
		
		return solicitacaoEntregaService.solicitar(entregra);
	}

	public EntregaController(SolicitacaoEntregaService solicitacaoEntregaService) {
		super();
		this.solicitacaoEntregaService = solicitacaoEntregaService;
	}

	
}
