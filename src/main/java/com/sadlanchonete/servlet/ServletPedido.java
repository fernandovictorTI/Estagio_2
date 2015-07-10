package com.sadlanchonete.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sadlanchonete.daos.FuncionarioDao;
import com.sadlanchonete.daos.PedidoDao;
import com.sadlanchonete.daos.ProdutoDao;
import com.sadlanchonete.entidade.Funcionario;
import com.sadlanchonete.entidade.FuncionarioView;
import com.sadlanchonete.entidade.Pedido;
import com.sadlanchonete.entidade.Produto;
import com.sadlanchonete.entidade.ProdutoView;

@WebServlet("/ServletPedido")
public class ServletPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletPedido() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String json = "";
		
		switch (request.getParameter("tipo")) {		
		case "funcionario":
			
			FuncionarioDao funcionarioDao = new FuncionarioDao();
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
			
			break;
			
		case "produto":
			
			ProdutoDao produtoDao = new ProdutoDao();
			List<ProdutoView> produtosView = new ArrayList<ProdutoView>();
            
			for(Produto produto : produtoDao.getAll()){
				ProdutoView produtoView = new ProdutoView();
				produtoView.setId(produto.getId());
				produtoView.setNomeProduto(produto.getNomeProduto());
				produtoView.setPreco(produto.getPreco());
				produtosView.add(produtoView);
			}
			
			json = new Gson().toJson(produtosView);
			
			break;
			
		case "Pedido":
			PedidoDao pedidoDao = new PedidoDao();
			int retorno = pedidoDao.getAll().size();			
			
			Pedido pedido = new Pedido();
			pedido.setNumeroPedido(retorno++);
			pedido.setDataPedido(new Date());
			
			json = new Gson().toJson(pedido);
			break;
		}
		
		response.setContentType("application/json");
		response.getWriter().write(json);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
