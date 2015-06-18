package com.sadlanchonete.daos;

import com.sadlanchonete.entidade.Produto;
import com.sadlanchonete.repositorio.RepositorioBase;

public class ProdutoDao extends RepositorioBase<Produto> {

	public ProdutoDao()  {
		super(Produto.class);
	}

}
