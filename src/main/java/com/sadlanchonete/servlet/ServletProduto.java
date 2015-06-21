package com.sadlanchonete.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sadlanchonete.daos.ProdutoDao;
import com.sadlanchonete.entidade.Produto;

@WebServlet("/ServletProduto")
public class ServletProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletProduto() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ProdutoDao produtoDao = new ProdutoDao();
		String json = "";

		if (request.getParameterMap().containsKey("id")) {

			int id = Integer.parseInt(request.getParameter("id"));
			Produto produto = produtoDao.getById(id);

			if (request.getParameterMap().containsKey("modo")) {
				produtoDao.remove(produto);
			} else {
				json = new Gson().toJson(produto);
			}
		} else {
			json = new Gson().toJson(produtoDao.getAll());
		}

		response.setContentType("application/json");
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(
				request.getInputStream()));
		String json = "";
		if (br != null) {
			json = br.readLine();
		}

		Produto produto = new Produto();

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		produto = gson.fromJson(json, Produto.class);

		ProdutoDao produtoDao = new ProdutoDao();
		produtoDao.add(produto);
	}

}
