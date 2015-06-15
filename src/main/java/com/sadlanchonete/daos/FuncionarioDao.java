package com.sadlanchonete.daos;

import com.sadlanchonete.entidade.Funcionario;
import com.sadlanchonete.repositorio.RepositorioBase;

public class FuncionarioDao extends RepositorioBase<Funcionario>{
	public FuncionarioDao() {
        super(Funcionario.class);
    }
}
