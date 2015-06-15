package com.sadlanchonete.repositorio;

import java.util.List;

public interface IRepositorioBase<T> {
    void add(T obj);
    void remove(T obj);
    void update(T obj);
    T getById(int id);
    List<T> getAll();
}
