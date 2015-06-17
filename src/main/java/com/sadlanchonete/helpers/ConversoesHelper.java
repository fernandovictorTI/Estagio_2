package com.sadlanchonete.helpers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ConversoesHelper {

	public static java.sql.Date formataData(String data) throws Exception {   
        if (data == null || data.equals(""))  
            return null;  
          
        java.sql.Date date = null;  
        try {  
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
            date = new java.sql.Date( ((java.util.Date)formatter.parse(data)).getTime() );  
        } catch (ParseException e) {              
            throw e;  
        }  
        return date;  
    }  

}
