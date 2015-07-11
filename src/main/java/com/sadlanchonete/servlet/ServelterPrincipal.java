package com.sadlanchonete.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sadlanchonete.daos.ComponenteDao;
import com.sadlanchonete.daos.ProdutoDao;
import com.sadlanchonete.entidade.Componente;
import com.sadlanchonete.entidade.Produto;
import com.sadlanchonete.entidade.ProdutoComponente;

@WebServlet("/ServelterPrincipal")
public class ServelterPrincipal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServelterPrincipal() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static class DataObject {
		private int qtdProduto;
		private int qtdComponente;

		public int getQtdProduto() {
			return qtdProduto;
		}

		public void setQtdProduto(int qtdProduto) {
			this.qtdProduto = qtdProduto;
		}

		public int getQtdComponente() {
			return qtdComponente;
		}

		public void setQtdComponente(int qtdComponente) {
			this.qtdComponente = qtdComponente;
		}

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String json = "";
		ProdutoDao produtoDao = new ProdutoDao();
		ComponenteDao componenteDao = new ComponenteDao();
		
		List<Componente> componentes = componenteDao.getAll();
		List<Produto> produtos = produtoDao.getAll();

		switch (request.getParameter("tipo")) {

		case "principal":
			
			int qtdProduto = produtos.size();			
			int qtdComponente = componentes.size();

			DataObject dataObject = new DataObject();
			dataObject.setQtdComponente(qtdComponente);
			dataObject.setQtdProduto(qtdProduto);

			json = new Gson().toJson(dataObject);
			
			break;

		case "relatorio":
			
			List<Componente> listAuxiliar = new ArrayList<Componente>();
			
			for(Componente componente : componentes){
				
				int quantiadade = 0;
				
				for(Produto produto : produtos){
					for(ProdutoComponente produtoComponente : produto.getProdutoComponentes()){
						if(componente.getId() == produtoComponente.getComponente().getId()){
							quantiadade += produtoComponente.getQuantidadeNecessaria();
						}
					}
				}
				
				if(quantiadade > componente.getQuantidade()){
					componente.setQuantidade(quantiadade - componente.getQuantidade());
					listAuxiliar.add(componente);
				}
			}
			
			json = new Gson().toJson(listAuxiliar);
			
			break;

		}
		
		response.setContentType("application/json");
		response.getWriter().write(json);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
