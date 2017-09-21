package com.meiduimall.service.settlement.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.meiduimall.core.BaseApiCode;
import com.meiduimall.exception.DaoException;
import com.meiduimall.service.settlement.dao.BaseMapper;

@Repository
public class BaseMapperImpl extends SqlSessionDaoSupport implements BaseMapper {
	
	private static final Logger log = LoggerFactory.getLogger(BaseMapperImpl.class);
	
	private static final String LOG_TAG = "sqlTag：{},params：{}";
	
    @Resource  
    @Override
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){  
        super.setSqlSessionFactory(sqlSessionFactory);  
    }
    
    @Override
	public <T, P> T selectOne(P params, String sqlTag) {
		try {
			return getSqlSession().selectOne(sqlTag, params);
		} catch (RuntimeException e) {
			log.error(LOG_TAG, sqlTag, params, e);
			throw new DaoException(BaseApiCode.EXCEPTION_UNKNOWN,BaseApiCode.getZhMsg(BaseApiCode.EXCEPTION_UNKNOWN));
		}
	}

	@Override
	public <T, P> List<T> selectList(P params, String sqlTag) {
		try {
			return getSqlSession().selectList(sqlTag, params);
		} catch (RuntimeException e) {
			log.error(LOG_TAG, sqlTag, params, e);
			throw new DaoException(BaseApiCode.EXCEPTION_UNKNOWN,BaseApiCode.getZhMsg(BaseApiCode.EXCEPTION_UNKNOWN));
		}
	}
	
	@Override
	public <T> Integer insert(T params, String sqlTag) {
		try {
			return getSqlSession().insert(sqlTag, params);
		} catch (RuntimeException e) {
			log.error(LOG_TAG, sqlTag, params, e);
			throw new DaoException(BaseApiCode.EXCEPTION_UNKNOWN,BaseApiCode.getZhMsg(BaseApiCode.EXCEPTION_UNKNOWN));
		}
	}

	@Override
	public <T> Integer insertBatch(List<T> params, String sqlTag) {
		try {
			return getSqlSession().insert(sqlTag + "." + sqlTag, params);
		} catch (RuntimeException e) {
			log.error(LOG_TAG, sqlTag, params, e);
			throw new DaoException(BaseApiCode.EXCEPTION_UNKNOWN,BaseApiCode.getZhMsg(BaseApiCode.EXCEPTION_UNKNOWN));
		}
	}

	@Override
	public <P> Integer update(P params, String sqlTag) {
		try {
			return getSqlSession().update(sqlTag, params);
		} catch (RuntimeException e) {
			log.error(LOG_TAG, sqlTag, params, e);
			throw new DaoException(BaseApiCode.EXCEPTION_UNKNOWN,BaseApiCode.getZhMsg(BaseApiCode.EXCEPTION_UNKNOWN));
		}
	}

	@Override
	public <P> Integer delete(P params, String sqlTag) {
		try {
			return getSqlSession().delete(sqlTag, params);
		} catch (RuntimeException e) {
			log.error(LOG_TAG, sqlTag, params, e);
			throw new DaoException(BaseApiCode.EXCEPTION_UNKNOWN,BaseApiCode.getZhMsg(BaseApiCode.EXCEPTION_UNKNOWN));
		}
	}

	@Override
	public <P> Integer deleteBatch(List<P> params, String sqlTag) {
		try {
			return getSqlSession().delete(sqlTag, params);
		} catch (RuntimeException e) {
			log.error(LOG_TAG, sqlTag, params, e);
			throw new DaoException(BaseApiCode.EXCEPTION_UNKNOWN,BaseApiCode.getZhMsg(BaseApiCode.EXCEPTION_UNKNOWN));
		}
	}

}