package com.meiduimall.service.member.service.impl;

import java.util.HashMap;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.service.member.constant.SysParamsConst;
import com.meiduimall.service.member.dao.BaseDao;
import com.meiduimall.service.member.model.MSAccount;
import com.meiduimall.service.member.service.MoneyService;
import com.meiduimall.service.member.util.DoubleCalculate;

@Service
public class MoneyServiceImpl  implements MoneyService{
	
	private final static Logger logger=LoggerFactory.getLogger(MoneyServiceImpl.class);

	@Autowired
	private BaseDao baseDao;

	public String getTotalMoney(String memId)
	{
		Map<String,Object> map=new HashMap<>();
		map.put("memid",memId);
		map.put("type",SysParamsConst.ACCOUNT_TYPE_MONEY);
		MSAccount account=null;
		Double totalmoney=null;
		try {
			account = baseDao.selectOne(map,"MSAccountMapper.getAccountByMemId");
			if(account != null){
				Double balance = Double.valueOf(account.getBalance());
				Double freezeBalance = Double.valueOf(account.getFreezeBalance());
				totalmoney = DoubleCalculate.sub(balance, freezeBalance);
			}
		} catch (Exception e) {
			logger.error("查询汇总余额操作失败:{}",e.getMessage());
		}
		return String.valueOf(totalmoney);
	}
	
}
