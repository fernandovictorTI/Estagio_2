package com.sadlanchonete.dao.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
 
public class HibernateUtil {
 
    private static final SessionFactory sessionFactory = buildSessionFactory();
 
    private static SessionFactory buildSessionFactory() {        
 
        try {
            Configuration configuration=new Configuration().configure(); // configures settings from hibernate.cfg.xml
 
            StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
             
            serviceRegistryBuilder.applySettings(configuration.getProperties());
 
            ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
            return configuration.buildSessionFactory(serviceRegistry);
        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
