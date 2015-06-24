package com.sadlanchonete.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sadlanchonete.daos.ComponenteDao;

@WebServlet("/ServletProdutoComponente")
public class ServletProdutoComponente extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletProdutoComponente() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComponenteDao componenteDao = new ComponenteDao();
		
		String json = "";		
		json = new Gson().toJson(componenteDao.getAll());
		
		response.setContentType("application/json");
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
