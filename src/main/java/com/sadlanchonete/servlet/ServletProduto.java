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

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sadlanchonete.daos.ProdutoDao;
import com.sadlanchonete.entidade.Componente;
import com.sadlanchonete.entidade.Produto;
import com.sadlanchonete.entidade.ProdutoComponente;

@WebServlet("/ServletProduto")
public class ServletProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletProduto() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T initializeAndUnproxy(T entity) {
        if (entity == null) {
            throw new 
               NullPointerException("Entity passed for initialization is null");
        }

        Hibernate.initialize(entity);
        if (entity instanceof HibernateProxy) {
            entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer()
                    .getImplementation();
        }
        return entity;
    }

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {

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
				//List<Produto> produtosClonado = ArrayList<Produto>();
				
				Gson gson = new GsonBuilder().disableInnerClassSerialization().create();
				json = gson.toJson(produtoDao.getAll());
			}

			response.setContentType("application/json");
			response.getWriter().write(json);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {

			BufferedReader br = new BufferedReader(new InputStreamReader(
					request.getInputStream()));
			String json = "";
			if (br != null) {
				json = br.readLine();
			}

			Produto produto = new Produto();

			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			produto = gson.fromJson(json, Produto.class);

			ProdutoComponente produtoComponente;
			List<ProdutoComponente> lstProdutosComponente = new ArrayList<ProdutoComponente>();

			if(produto.getComponentes() == null){
				produto.setComponentes(new ArrayList<Componente>());
			}
			
			for (Componente componente : produto.getComponentes()) {
				produtoComponente = new ProdutoComponente();
				produtoComponente.setComponente(componente);
				produtoComponente.setProduto(produto);
				lstProdutosComponente.add(produtoComponente);
			}

			produto.setProdutoComponentes(lstProdutosComponente);

			ProdutoDao produtoDao = new ProdutoDao();
			if (produto.getId() > 0) {
				produtoDao.update(produto);
			} else {
				produtoDao.add(produto);
			}
		} catch (Exception e) {
			try {
				throw new Exception(e.getMessage());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}

