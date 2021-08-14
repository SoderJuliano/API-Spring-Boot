package com.juliano.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.juliano.api.model.OcorrenciaModel;
import com.juliano.domain.model.Ocorrencia;

@Component
public class OcorrenciaAssembler {
	
	private ModelMapper modelMapper;
	
	public OcorrenciaModel toModel(Ocorrencia ocorrencia) {
		return modelMapper.map(ocorrencia, OcorrenciaModel.class);
	}

	public OcorrenciaAssembler(ModelMapper modelMapper) {
		super();
		this.modelMapper = modelMapper;
	}
	
	public List<OcorrenciaModel> toCollectionModel(List<Ocorrencia> ocorrencias){
		return ocorrencias.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}


}