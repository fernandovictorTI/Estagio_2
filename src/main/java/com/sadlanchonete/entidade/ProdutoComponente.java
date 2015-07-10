package com.sadlanchonete.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "seq", sequenceName = "seq_produto_componente",
                   allocationSize = 1, initialValue = 1)
public class ProdutoComponente {

	@Id
	@GeneratedValue(generator="seq")
	private int id;

	@ManyToOne
	@JoinColumn(name = "id_produto", updatable = true, insertable = true)
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(name = "id_componente", updatable = true, insertable = true)
	private Componente componente;
	
	@Column
	private int quantidadeNecessaria;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Componente getComponente() {
		return componente;
	}

	public void setComponente(Componente componente) {
		this.componente = componente;
	}

	public int getQuantidadeNecessaria() {
		return quantidadeNecessaria;
	}

	public void setQuantidadeNecessaria(int quantidadeNecessaria) {
		this.quantidadeNecessaria = quantidadeNecessaria;
	}

}
