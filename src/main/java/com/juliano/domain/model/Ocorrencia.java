package com.juliano.domain.model;

import java.time.OffsetDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Ocorrencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Entrega entrega;
	
	private String descricao;
	
	private OffsetDateTime dataRegistro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Entrega getEntrega() {
		return entrega;
	}

	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public OffsetDateTime getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(OffsetDateTime dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataRegistro, descricao, entrega, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ocorrencia other = (Ocorrencia) obj;
		return Objects.equals(dataRegistro, other.dataRegistro) && Objects.equals(descricao, other.descricao)
				&& Objects.equals(entrega, other.entrega) && Objects.equals(id, other.id);
	}
	
	
	
}
