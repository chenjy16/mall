package com.meiduimall.service.member.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meiduimall.service.member.dao.BaseDao;
import com.meiduimall.service.member.service.PointsService;
import com.meiduimall.service.member.util.DoubleCalculate;

@Service
public class PointsServiceImpl implements PointsService {
	
	private final static Logger logger=LoggerFactory.getLogger(PointsServiceImpl.class);

	@Autowired
	private BaseDao baseDao;
	
	public String getTotalPoints(String memId,String currentPoints) {
		Double avaliablePoints = Double.valueOf("0");
		try{
			/**冻结解冻的积分总额*/
			String fzPoints =baseDao.selectOne(memId,"MSConsumePointsFreezeInfoMapper.getSumConsumePointsByMemId");
			avaliablePoints = DoubleCalculate.add(Double.valueOf(fzPoints),
					Double.valueOf(currentPoints));
		}catch(Exception e){
			logger.error("查询汇总积分操作失败:{}",e.getMessage());
		}
		return String.valueOf(avaliablePoints);
	}
	
}
