package com.sadlanchonete.entidade;

import java.util.ArrayList;
import java.util.List;

public class ProdutoView {

	private int id;
	private String nomeProduto;
	private float preco;
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

	public List<Componente> getComponentes() {
		return componentes;
	}

	public void setComponentes(List<Componente> componentes) {
		this.componentes = componentes;
	}
	
	public void setComponente(Componente componente){
		if(this.componentes == null)
			this.componentes = new ArrayList<Componente>();
		this.componentes.add(componente);
	}
}
