package com.sadlanchonete.entidade;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

@Entity
@SequenceGenerator(name = "seq", sequenceName = "seq_produto",
                   allocationSize = 1, initialValue = 1)
public class Produto {

	@Id
	@GeneratedValue(generator="seq")
	private int id;
	
	@Column(length = 24, nullable = false)
	private String nomeProduto;
	
	@Column(nullable = false)
	private float preco;
	
	@OneToMany(mappedBy = "produto", targetEntity = ProdutoComponente.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ProdutoComponente> produtoComponentes;

	@Transient
	private List<Componente> componentes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public List<ProdutoComponente> getProdutoComponentes() {
		return produtoComponentes;
	}

	public void setProdutoComponentes(List<ProdutoComponente> produtoComponentes) {
		this.produtoComponentes = produtoComponentes;
	}

	public List<Componente> getComponentes() {
		return componentes;
	}

	public void setComponentes(List<Componente> componentes) {
		this.componentes = componentes;
	}

}
