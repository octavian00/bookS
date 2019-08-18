package com.bookstore.dao;

import java.util.List;

public interface GeenericDAO<T> {
	public T create(T t);
	public T update(T t);
	public T get(Object id);
	public List<T> listAll();
	public void delete(Object id);
	public long count();
}
