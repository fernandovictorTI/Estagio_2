package com.sadlanchonete.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import javax.faces.convert.FloatConverter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

import com.sadlanchonete.daos.FuncionarioDao;
import com.sadlanchonete.daos.ProdutoDao;
import com.sadlanchonete.entidade.Funcionario;
import com.sadlanchonete.entidade.Produto;
import com.sadlanchonete.entidade.Telefone;
import com.sadlanchonete.helpers.ConversoesHelper;

/**
 * Servlet implementation class ServletProduto
 */
@WebServlet("/ServletProduto")
public class ServletProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProduto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json = "";
        if(br != null){
            json = br.readLine();
        }
        
        Produto produto = new Produto();
        
        try {			
			JSONObject produtoJson = new JSONObject(json);  
			produto.setId(produtoJson.getInt("id"));
			produto.setNomeProduto(produtoJson.getString("nomeProduto"));
			
			String precoProduto = produtoJson.getString("preco");
			
			produto.setPreco(Float.parseFloat(precoProduto));
			
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        ProdutoDao produtoDao = new ProdutoDao();
        produtoDao.add(produto);
	}

}
