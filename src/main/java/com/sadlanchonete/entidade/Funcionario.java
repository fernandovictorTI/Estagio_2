package com.sadlanchonete.entidade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.sadlanchonete.daos.FuncionarioDao;

@Entity
@SequenceGenerator(name = "seq", sequenceName = "seq_funcionario",
                   allocationSize = 1, initialValue = 1)
public class Funcionario  {

	@Id
	@GeneratedValue(generator="seq")
	private int id;

	@Column(length = 14)
	private String cpf;
	
	@Column(length = 80)
	private String nome;
	
	@Column(length = 20)
	private String rg;
	
	@Column(name = "data_nascimento")
	private Date dataNascimento;
	
	@Column(length = 1)
	private String sexo;
	
	@Column(length = 1, name = "estado_civil")
	private String estadoCivil;
	
	@Column(length = 80)
	private String email;
	
	@Column(length = 20)
	private String senha;
	
	@OneToMany(mappedBy = "funcionario", targetEntity = Telefone.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Telefone> telefones;
	
	@OneToMany(mappedBy = "funcionario", targetEntity = Endereco.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Endereco> enderecos;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	
	public List<Telefone> getTelefones() {
		return telefones;
	}
	
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
	
	public void setTelefone(Telefone telefone) {
		if(telefones == null){
			telefones = new ArrayList<Telefone>();
		}
		this.telefones.add(telefone);
	}
	
	public boolean temEmailCadastrado(Funcionario funcionario){
		FuncionarioDao dao = new FuncionarioDao();		
		return dao.temEmailCadastado(funcionario.getEmail());
	}	

}
