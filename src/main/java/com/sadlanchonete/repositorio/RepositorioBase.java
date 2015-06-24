package com.sadlanchonete.repositorio;

import java.util.List;

import org.hibernate.Session;

import com.sadlanchonete.dao.utils.HibernateUtil;

public abstract class RepositorioBase<T> implements IRepositorioBase<T> {

	private Class<T> persistentClass;

	public RepositorioBase(Class<T> pclass) {
		this.persistentClass = pclass;
		getSession();
	}

	private Session session;

	public Session getSession() {
		if (session == null) {
			session = HibernateUtil.getSessionFactory().openSession();
		}

		return session;
	}

	@Override
	public void add(T obj) {
		try {
			session.beginTransaction();
			session.save(obj);
			session.getTransaction().commit();
		} catch (Exception erro) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	@Override
	public void remove(T obj) {
		try {
			session.beginTransaction();
			session.delete(obj);
			session.getTransaction().commit();
		} catch (Exception erro) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}

	}

	@Override
	public void update(T obj) {
		try {
			session.beginTransaction();
			session.update(obj);
			session.getTransaction().commit();
		} catch (Exception erro) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
	}

	@Override
	public T getById(int id) {
		@SuppressWarnings("unchecked")
		T t = (T) session.get(persistentClass, id);
		return t;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() {
		return session.createCriteria(persistentClass).list();
	}

}
