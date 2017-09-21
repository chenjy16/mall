package com.meiduimall.payment.api.dao;

import java.util.List;


public interface DaoTemplate {
	
	public int insert(String id) throws Exception;
	public <T> int insert(String id, T clasz)throws Exception;
	
	public <T> List<T> findAll(String id)throws Exception;
	public <T, P> List<T> findAll(String id, P clasz)throws Exception;
	
	public <T> T find(String id)throws Exception;
	public <T, P> T find(String id, P clasz)throws Exception;
	
	public int update(String id)throws Exception;
	public <T> int update(String id, T clasz)throws Exception;
	
	public int delete(String id)throws Exception;
	public <T> int delete(String id, T clasz)throws Exception;
	
}
