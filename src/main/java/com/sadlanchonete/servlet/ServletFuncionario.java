package com.sadlanchonete.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

import com.google.gson.Gson;
import com.sadlanchonete.daos.FuncionarioDao;
import com.sadlanchonete.entidade.Funcionario;
import com.sadlanchonete.entidade.Telefone;
import com.sadlanchonete.helpers.ConversoesHelper;

@WebServlet("/ServletFuncionario")
public class ServletFuncionario extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletFuncionario() {
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
        
        Funcionario funcionario = new Funcionario();
        
        try {			
			JSONObject funcionarioJson = new JSONObject(json);  
			funcionario.setId(funcionarioJson.getInt("id"));
			funcionario.setNome(funcionarioJson.getString("nome"));
			funcionario.setCpf(funcionarioJson.getString("cpf"));
			funcionario.setEmail(funcionarioJson.getString("email"));
			funcionario.setEstadoCivil(funcionarioJson.getString("estadoCivil"));			
			funcionario.setRg(funcionarioJson.getString("rg"));
			funcionario.setSenha(funcionarioJson.getString("senha"));
			funcionario.setSexo(funcionarioJson.getString("sexo"));
			new ConversoesHelper();
			Date dataNascimento = ConversoesHelper.formataData(funcionarioJson.getString("dataNascimento"));
			funcionario.setDataNascimento(dataNascimento);
			
			JSONArray arrTelefones = funcionarioJson.getJSONArray("telefones");
				
			Telefone telefone = null;
			
			for (int i=0; i < arrTelefones.length(); i++) { 
				String numeroTelefone = arrTelefones.getString(i);
				telefone = new Telefone();
				telefone.setTelefone(numeroTelefone);
				telefone.setFuncionario(funcionario);
				funcionario.setTelefone(telefone);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        funcionarioDao.add(funcionario);
	}

}
