package com.sadlanchonete.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sadlanchonete.daos.FuncionarioDao;
import com.sadlanchonete.entidade.Endereco;
import com.sadlanchonete.entidade.Funcionario;
import com.sadlanchonete.entidade.FuncionarioView;
import com.sadlanchonete.entidade.Telefone;

@WebServlet("/ServletFuncionario")
public class ServletFuncionario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletFuncionario() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {

			FuncionarioDao funcionarioDao = new FuncionarioDao();
			String json = "";

			if (request.getParameterMap().containsKey("id")) {

				int id = Integer.parseInt(request.getParameter("id"));
				Funcionario funcionario = funcionarioDao.getById(id);

				if (request.getParameterMap().containsKey("modo")) {
					funcionarioDao.remove(funcionario);
				} else {
					json = new Gson().toJson(funcionario);
				}
			} else {

				List<FuncionarioView> funcionariosView = new ArrayList<FuncionarioView>();

				for (Funcionario funcionario : funcionarioDao.getAll()) {
					FuncionarioView funcionarioView = new FuncionarioView();
					funcionarioView.setId(funcionario.getId());
					funcionarioView.setEmail(funcionario.getEmail());
					funcionarioView.setNome(funcionario.getNome());
					funcionarioView.setCpf(funcionario.getCpf());
					funcionarioView.setStatus(funcionario.getStatus());
					funcionariosView.add(funcionarioView);
				}

				json = new Gson().toJson(funcionariosView);
			}

			response.setContentType("application/json");
			response.getWriter().write(json);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		FuncionarioDao funcionarioDao = new FuncionarioDao();

		if (request.getParameterMap().containsKey("id")) {

			int id = Integer.parseInt(request.getParameter("id"));
			Funcionario funcionario = funcionarioDao.getById(id);
			funcionario.setStatus(0);
			funcionarioDao.update(funcionario);

		} else {

			try {

				BufferedReader br = new BufferedReader(new InputStreamReader(
						request.getInputStream()));
				String json = "";
				if (br != null) {
					json = br.readLine();
				}

				Funcionario funcionario = new Funcionario();

				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd")
						.create();
				funcionario = gson.fromJson(json, Funcionario.class);

				for (Telefone telefone : funcionario.getTelefones()) {
					telefone.setFuncionario(funcionario);
				}

				for (Endereco endereco : funcionario.getEnderecos()) {
					endereco.setFuncionario(funcionario);
				}

				if (funcionario.getId() > 0) {
					funcionarioDao.update(funcionario);
				} else {
					funcionario.setStatus(1);
					funcionarioDao.add(funcionario);
				}

			} catch (Exception e) {
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write("ERRO");
			}

		}
	}

}
