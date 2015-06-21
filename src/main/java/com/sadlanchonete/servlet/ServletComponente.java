package com.sadlanchonete.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sadlanchonete.daos.ComponenteDao;
import com.sadlanchonete.entidade.Componente;

@WebServlet("/ServletComponente")
public class ServletComponente extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletComponente() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = "";
        if(br != null){
            json = br.readLine();
        }
        
        Componente componente = new Componente();
        
        Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        componente = gson.fromJson(json, Componente.class);        
               
        ComponenteDao componenteDao = new ComponenteDao();
        componenteDao.add(componente);
		
	}

}
