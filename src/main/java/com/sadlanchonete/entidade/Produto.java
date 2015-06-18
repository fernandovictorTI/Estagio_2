package com.sadlanchonete.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "seq", sequenceName = "seq_produto",
                   allocationSize = 1, initialValue = 1)
public class Produto {

	@Id
	@GeneratedValue(generator="seq")
	private int id;
	
	@Column(length = 24)
	private String nomeProduto;
	
	@Column()
	private float preco;

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

}
