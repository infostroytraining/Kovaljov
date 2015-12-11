package ua.nure.infostroy.dao;

public interface CRUD<T> {
	T insert(T object);
	
	T get(long objectId);
	
	boolean update(T object);
	
	boolean delete(long objectId);
	
}
