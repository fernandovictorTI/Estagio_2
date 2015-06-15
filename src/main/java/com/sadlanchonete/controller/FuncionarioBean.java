package com.sadlanchonete.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.sadlanchonete.daos.FuncionarioDao;
import com.sadlanchonete.entidade.Funcionario;

@ManagedBean(name = "funcionarioBean")
@RequestScoped
public class FuncionarioBean {

	private Funcionario funcionario = null;
	private FuncionarioDao funcionarioDao = null;
	private List<Funcionario> funcionarios = null;

	@PostConstruct
    public void init() {
        funcionario = new Funcionario();
        funcionarioDao = new FuncionarioDao();
    }
	
	public void add(){
		funcionarioDao.add(funcionario);
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getFuncionarios() {
		funcionarios = funcionarioDao.getAll();
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}	

}
