package br.agenda3.dao;

import java.io.Serializable;

 
public interface GenericDao<T> {

	T find(Serializable id);

	void persist(T t);

	void merge(T t);

	void remove(T t);

	boolean searchByAttribute(String attributeName, String attributeValue);
}
