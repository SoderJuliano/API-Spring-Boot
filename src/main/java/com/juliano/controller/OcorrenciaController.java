package com.juliano.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.juliano.api.model.OcorrenciaModel;
import com.juliano.api.model.input.OcorrenciaInput;
import com.juliano.assembler.OcorrenciaAssembler;
import com.juliano.domain.model.Entrega;
import com.juliano.domain.model.Ocorrencia;
import com.juliano.domain.model.service.BuscaEntregaService;
import com.juliano.domain.model.service.RegistroOcorrenciaService;

@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {
	
	private RegistroOcorrenciaService registroOcorrenciaService;
	private OcorrenciaAssembler ocorrenciaAssembler;
	private BuscaEntregaService buscaEntregaService;
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcorrenciaModel registrar(@PathVariable Long entregaId,
		@Valid @RequestBody OcorrenciaInput occorenciaInput) {
		Ocorrencia ocorrenciaRegistrada = registroOcorrenciaService.registrar(entregaId, occorenciaInput.getDescricao());
		
		return ocorrenciaAssembler.toModel(ocorrenciaRegistrada);
	}




	public OcorrenciaController(RegistroOcorrenciaService registroOcorrenciaService,
			OcorrenciaAssembler ocorrenciaAssembler, BuscaEntregaService buscaEntregaService) {
		super();
		this.registroOcorrenciaService = registroOcorrenciaService;
		this.ocorrenciaAssembler = ocorrenciaAssembler;
		this.buscaEntregaService = buscaEntregaService;
	}




	@GetMapping
	public List<OcorrenciaModel> listar(@PathVariable Long entregaId){
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		
		return ocorrenciaAssembler.toCollectionModel( entrega.getOcorencias());
		
	}
	
}
