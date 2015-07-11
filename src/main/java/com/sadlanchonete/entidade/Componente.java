package com.sadlanchonete.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

@Entity
@SequenceGenerator(name = "seq", sequenceName = "seq_componente",
                   allocationSize = 1, initialValue = 1)
public class Componente {

	@Id
	@GeneratedValue(generator="seq")
	private int id;
	
	@Column(length = 60, nullable = false)
	private String nomeComponente;
	
	@Column(length = 60, nullable = false)
	private String tipoComponente;
	
	@Column(length = 60, nullable = false)
	private String undMedida;
	
	@Column
	private int quantidade;
	
	@Transient
	private int quantidadeNecessaria;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeComponente() {
		return nomeComponente;
	}

	public void setNomeComponente(String nomeComponente) {
		this.nomeComponente = nomeComponente;
	}

	public String getTipoComponente() {
		return tipoComponente;
	}

	public void setTipoComponente(String tipoComponente) {
		this.tipoComponente = tipoComponente;
	}

	public String getUndMedida() {
		return undMedida;
	}

	public void setUndMedida(String undMedida) {
		this.undMedida = undMedida;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getQuantidadeNecessaria() {
		return quantidadeNecessaria;
	}

	public void setQuantidadeNecessaria(int quantidadeNecessaria) {
		this.quantidadeNecessaria = quantidadeNecessaria;
	}

}
