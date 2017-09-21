package com.meiduimall.payment.api.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import com.meiduimall.exception.DaoException;
import com.meiduimall.payment.api.constant.ServicePaymentApiCode;
import com.meiduimall.payment.api.dao.DaoTemplate;



@Component
public class DaoTemplateImpl implements DaoTemplate {
	
	@Resource
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int insert(String id){
		try {
			return sqlSessionTemplate.insert(id);
		} catch (Exception e) {
			throw new DaoException(ServicePaymentApiCode.UNKNOWN_ERROR,ServicePaymentApiCode.getZhMsg(ServicePaymentApiCode.UNKNOWN_ERROR),e);
		}
	}

	@Override
	public <T> int insert(String id, T clasz){
		try {
			return sqlSessionTemplate.insert(id, clasz);
		} catch (Exception e) {
			throw new DaoException(ServicePaymentApiCode.UNKNOWN_ERROR,ServicePaymentApiCode.getZhMsg(ServicePaymentApiCode.UNKNOWN_ERROR),e);
		}
	}

	@Override
	public  <T> List<T> findAll(String id){
		try {
			return sqlSessionTemplate.selectList(id);
		} catch (Exception e) {
			throw new DaoException(ServicePaymentApiCode.UNKNOWN_ERROR,ServicePaymentApiCode.getZhMsg(ServicePaymentApiCode.UNKNOWN_ERROR),e);
		}
	}

	@Override
	public <T, P> List<T> findAll(String id, P clasz){
		try {
			return sqlSessionTemplate.selectList(id, clasz);
		} catch (Exception e) {
			throw new DaoException(ServicePaymentApiCode.UNKNOWN_ERROR,ServicePaymentApiCode.getZhMsg(ServicePaymentApiCode.UNKNOWN_ERROR),e);
		}
	}

	@Override
	public <T> T find(String id){
		try {
			return sqlSessionTemplate.selectOne(id);
		} catch (Exception e) {
			throw new DaoException(ServicePaymentApiCode.UNKNOWN_ERROR,ServicePaymentApiCode.getZhMsg(ServicePaymentApiCode.UNKNOWN_ERROR),e);
		}
	}

	@Override
	public <T, P> T find(String id, P clasz){
		try {
			return sqlSessionTemplate.selectOne(id, clasz);
		} catch (Exception e) {
			throw new DaoException(ServicePaymentApiCode.UNKNOWN_ERROR,ServicePaymentApiCode.getZhMsg(ServicePaymentApiCode.UNKNOWN_ERROR),e);
		}
	}

	@Override
	public int update(String id){
		try {
			return sqlSessionTemplate.update(id);
		} catch (Exception e) {
			throw new DaoException(ServicePaymentApiCode.UNKNOWN_ERROR,ServicePaymentApiCode.getZhMsg(ServicePaymentApiCode.UNKNOWN_ERROR),e);
		}
	}

	@Override
	public <T> int update(String id, T clasz){
		try {
			return sqlSessionTemplate.update(id, clasz);
		} catch (Exception e) {
			throw new DaoException(ServicePaymentApiCode.UNKNOWN_ERROR,ServicePaymentApiCode.getZhMsg(ServicePaymentApiCode.UNKNOWN_ERROR),e);
		}
	}

	@Override
	public int delete(String id){
		try {
			return sqlSessionTemplate.delete(id);
		} catch (Exception e) {
			throw new DaoException(ServicePaymentApiCode.UNKNOWN_ERROR,ServicePaymentApiCode.getZhMsg(ServicePaymentApiCode.UNKNOWN_ERROR),e);
		}
	}

	@Override
	public <T> int delete(String id, T clasz){
		try {
			return sqlSessionTemplate.delete(id, clasz);
		} catch (Exception e) {
			throw new DaoException(ServicePaymentApiCode.UNKNOWN_ERROR,ServicePaymentApiCode.getZhMsg(ServicePaymentApiCode.UNKNOWN_ERROR),e);
		}
	}
	
	
}
