package com.sadlanchonete.repositorio;

import java.util.List;
import org.hibernate.Session;
import com.sadlanchonete.dao.utils.HibernateUtil;

public abstract class RepositorioBase<T> implements IRepositorioBase<T> {

	private Class<T> persistentClass;

	public RepositorioBase(Class<T> pclass) {
		this.persistentClass = pclass;
	}

	@Override
	public void add(T obj) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(obj);
		session.getTransaction().commit();
	}

	@Override
	public void remove(T obj) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(obj);
		session.getTransaction().commit();
	}

	@Override
	public void update(T obj) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(obj);
		session.getTransaction().commit();
	}

	@Override
	public T getById(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (T)session.load(persistentClass, id);
	}

	@Override
	public List<T> getAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return session.createCriteria(persistentClass).list();
	}

}
