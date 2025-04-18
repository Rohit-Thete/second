package Dao;

import java.sql.SQLException;
import java.util.Collection;

import entity.Article;

public interface JdbcDao<T,K>{
	Boolean save(T t) throws SQLException;
	
	Collection<T> findAll()throws SQLException;
	
	T findById(K key)throws SQLException;

	void update(T t)throws SQLException;
	
	public Boolean delete(K key) throws SQLException;

}
