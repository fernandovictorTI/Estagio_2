package com.sadlanchonete.entidade;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "seq", sequenceName = "seq_produto_componente",
                   allocationSize = 1, initialValue = 1)
public class ProdutoComponente implements Serializable {

	@Id
	@GeneratedValue(generator="seq")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "id_produto", updatable = true, insertable = true, columnDefinition = "id_produto")
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(name = "id_componente", updatable = true, insertable = true, columnDefinition = "id_componente")
	private Componente componente;

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

}
