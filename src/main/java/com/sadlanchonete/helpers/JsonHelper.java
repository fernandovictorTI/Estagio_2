package com.sadlanchonete.helpers;

import java.util.List;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;

public class JsonHelper<T> {

	private Class<T> persistentClass;

	public JsonHelper(Class<T> pclass) {
		this.setPersistentClass(pclass);
	}

	public List<T> getListFromArrJson(JSONArray jsonArray) throws JSONException {
		return null;
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

}
