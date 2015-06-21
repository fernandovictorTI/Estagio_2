package com.sadlanchonete.daos;

import com.sadlanchonete.entidade.Pedido;
import com.sadlanchonete.repositorio.RepositorioBase;

public class PedidoDao extends RepositorioBase<Pedido> {

	public PedidoDao() {
		super(Pedido.class);
	}

}
