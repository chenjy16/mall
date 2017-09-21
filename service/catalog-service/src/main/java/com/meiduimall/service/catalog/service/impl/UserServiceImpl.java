package com.meiduimall.service.catalog.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.service.catalog.dao.BaseDao;
import com.meiduimall.service.catalog.entity.SysuserAccount;
import com.meiduimall.service.catalog.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(ShopServiceImpl.class);

	@Autowired
	private BaseDao baseDao;

	@Override
	public SysuserAccount getUserByMemId(String memId) {
		List<SysuserAccount> list = baseDao.selectList(memId, "SysuserAccountMapper.selectByMemId");
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		} else {
			return null;
		}
	}

}
