package com.sadlanchonete.helpers;

import java.util.List;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sadlanchonete.entidade.Funcionario;
import com.sadlanchonete.entidade.Telefone;

public class JsonHelper<T> {

	private Class<T> persistentClass;

	public JsonHelper(Class<T> pclass) {
		this.persistentClass = pclass;
	}

	public List<T> getListFromArrJson(JSONArray jsonArray) throws JSONException {

		T tClass = null;

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject objectJson = jsonArray.getJSONObject(i);
		}

		return null;
	}

}
