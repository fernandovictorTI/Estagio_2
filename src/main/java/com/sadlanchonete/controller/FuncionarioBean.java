package com.sadlanchonete.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.sadlanchonete.daos.FuncionarioDao;
import com.sadlanchonete.entidade.Funcionario;
import com.sadlanchonete.entidade.Telefone;

@ManagedBean(name = "funcionarioBean")
@RequestScoped
public class FuncionarioBean {

	private Funcionario funcionario = null;
	private Telefone telefone = null;
	private FuncionarioDao funcionarioDao = null;
	private List<Funcionario> funcionarios = null;

	@PostConstruct
	public void init() {
		funcionario = new Funcionario();
		telefone = new Telefone();
		funcionarioDao = new FuncionarioDao();
	}

	public void add() {
		funcionarioDao.add(funcionario);
		funcionario = new Funcionario();
	}

	public void addTelefone() {
		telefone.setFuncionario(funcionario);
		funcionario.setTelefone(telefone);
		telefone = new Telefone();
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

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

}
