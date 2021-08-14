package com.juliano.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.juliano.api.model.DestinatarioModel;
import com.juliano.api.model.EntregaModel;
import com.juliano.domain.model.Entrega;
import com.juliano.domain.model.service.SolicitacaoEntregaService;
import com.juliano.domain.repository.EntregaRepository;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

	@Autowired
	private EntregaRepository entregaRepository;
	private SolicitacaoEntregaService solicitacaoEntregaService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Entrega solicitar(@Valid @RequestBody Entrega entregra) {
		
		return solicitacaoEntregaService.solicitar(entregra);
	}

	public EntregaController(SolicitacaoEntregaService solicitacaoEntregaService) {
		super();
		this.solicitacaoEntregaService = solicitacaoEntregaService;
	}
	
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId){
		return entregaRepository.findById(entregaId)
				.map(entrega ->{
					EntregaModel entregaModel = new EntregaModel();
					entregaModel.setId(entrega.getId());
					entregaModel.setNomeCliente(entrega.getCliente().getNome());
					entregaModel.setDestinatario(new DestinatarioModel());
					entregaModel.getDestinatario().setNome(entrega.getDestinatario().getNome());
					entregaModel.getDestinatario().setLogradouro(entrega.getDestinatario().getLogradouro());
					entregaModel.getDestinatario().setNumero(entrega.getDestinatario().getNumero());
					entregaModel.getDestinatario().setComplemento(entrega.getDestinatario().getComplemento());
					entregaModel.getDestinatario().setBairro(entrega.getDestinatario().getBairro());
					entregaModel.setTaxa(entrega.getTaxa());
					entregaModel.setDataPedido(entrega.getDataPedido());
					entrega.setDataFinalizacao(entrega.getDataFinalizacao());
					return ResponseEntity.ok(entregaModel);
				}).orElse(ResponseEntity.notFound().build());
				}
	
	@GetMapping
	public List<Entrega> Listar() {
		return entregaRepository.findAll();
	}

	
}
