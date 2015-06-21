package com.sadlanchonete.daos;


import org.hibernate.Query;
import org.hibernate.Session;

import com.sadlanchonete.dao.utils.HibernateUtil;
import com.sadlanchonete.entidade.Funcionario;
import com.sadlanchonete.repositorio.RepositorioBase;

public class FuncionarioDao extends RepositorioBase<Funcionario>{
	public FuncionarioDao() {
        super(Funcionario.class);
    }
	
	public boolean temEmailCadastado(String email) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query q = session.createQuery("SELECT count(id) FROM Funcionario as o WHERE o.email = :email");
		q.setParameter("email", email);
		return (q.getFirstResult() != null);
	}
}
