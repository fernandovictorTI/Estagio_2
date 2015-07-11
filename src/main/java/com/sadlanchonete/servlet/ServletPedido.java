package com.sadlanchonete.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sadlanchonete.daos.ComponenteDao;
import com.sadlanchonete.daos.FuncionarioDao;
import com.sadlanchonete.daos.PedidoDao;
import com.sadlanchonete.daos.ProdutoDao;
import com.sadlanchonete.entidade.Componente;
import com.sadlanchonete.entidade.Funcionario;
import com.sadlanchonete.entidade.FuncionarioView;
import com.sadlanchonete.entidade.Item;
import com.sadlanchonete.entidade.Pedido;
import com.sadlanchonete.entidade.Produto;
import com.sadlanchonete.entidade.ProdutoComponente;
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
			
			json = new Gson().toJson(pedido);
			break;
		}
		
		response.setContentType("application/json");
		response.getWriter().write(json);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PedidoDao pedidoDao = new PedidoDao();
		
		try{
			
			BufferedReader br = new BufferedReader(new InputStreamReader(
					request.getInputStream()));
			String json = "";
			if (br != null) {
				json = br.readLine();
			}
			
			Pedido pedido = new Pedido();
			
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd")
					.create();
			pedido = gson.fromJson(json, Pedido.class);			
			pedido.setDataPedido(new Date());
			
			for(Item item : pedido.getItens()){
				item.setPedido(pedido);
			}
			
			pedidoDao.add(pedido);
			
			// Atualiza estoque
			ComponenteDao componenteDao = new ComponenteDao();
			ProdutoDao produtoDao = new ProdutoDao();
			
			for(Item item : pedido.getItens()){
				
				Produto produto = item.getProduto();
				produto = produtoDao.getById(produto.getId());

				for (ProdutoComponente produtoComponente : produto.getProdutoComponentes()) {
					Componente componente = produtoComponente.getComponente();
					componente.setQuantidade(componente.getQuantidade() - item.getQuantidade());
					componenteDao.update(componente);
				}				
			}
			
		} catch (Exception e) {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write("ERRO");
		}
	}

}
