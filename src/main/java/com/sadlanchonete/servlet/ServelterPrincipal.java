package com.sadlanchonete.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sadlanchonete.daos.ComponenteDao;
import com.sadlanchonete.daos.ProdutoDao;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String json = "";
		
		ProdutoDao produtoDao = new ProdutoDao();
		int qtdProduto = produtoDao.getAll().size();
		
		ComponenteDao componenteDao = new ComponenteDao();
		int qtdComponente = componenteDao.getAll().size();
		
		DataObject dataObject = new DataObject();
		dataObject.setQtdComponente(qtdComponente);
		dataObject.setQtdProduto(qtdProduto);
		
		json = new Gson().toJson(dataObject);
		
		response.setContentType("application/json");
		response.getWriter().write(json);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
