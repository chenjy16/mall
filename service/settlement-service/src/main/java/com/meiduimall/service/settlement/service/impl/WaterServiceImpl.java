package com.meiduimall.service.settlement.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.StringUtil;
import com.google.common.base.Strings;
import com.meiduimall.service.settlement.dao.BaseMapper;
import com.meiduimall.service.settlement.model.EcmMzfDrawWater;
import com.meiduimall.service.settlement.model.EcmMzfWater;
import com.meiduimall.service.settlement.service.AgentService;
import com.meiduimall.service.settlement.service.WaterService;
import com.meiduimall.service.settlement.vo.BilledWaterVO2Merge;

@Service
public class WaterServiceImpl implements WaterService {
	
	@Autowired
	private AgentService agentService;
	
	@Autowired
	private BaseMapper baseMapper;

	
	@Override
	public Map<String, Object> getWaterType1Detail(String waterId, String waterType){
		//获取提现流水详情
		EcmMzfWater ecmMzfWater = agentService.getWaterDetailByWaterId(waterId, waterType);
		if(ecmMzfWater != null && !Strings.isNullOrEmpty(ecmMzfWater.getExtId())){
			EcmMzfDrawWater drawWater = agentService.getDrawWaterInfo(ecmMzfWater.getExtId());
			
			if(ecmMzfWater.getMoney() != null && !StringUtil.isEmpty(drawWater.getRemark())){//remark字段存储的是提现手续费
				BigDecimal money = ecmMzfWater.getMoney();
				BigDecimal fee = new BigDecimal(drawWater.getRemark());
				if(money.compareTo(BigDecimal.ZERO) > 0){
					ecmMzfWater.setMoney(money.subtract(fee));//money-fee  money>0说明提现失败 无需扣手续费
				}else{
					ecmMzfWater.setMoney(money.add(fee));//money+fee money<0说明提现成功 需扣手续费
				}
			}
			
		}
		
		Map<String,Object> map = new HashMap<>();
		map.put("waterInfo", ecmMzfWater);
		return map;
	}

	
	@Override
	public Map<String, Object> getWaterDetail(String waterId, String waterType){
		//获取代理费或保证金流水详情
		EcmMzfWater ecmMzfWater = agentService.getWaterDetailByWaterId(waterId, waterType);
		Map<String,Object> map = new HashMap<>();
		map.put("waterInfo", ecmMzfWater);
		return map;
	}

	
	@Override
	public List<BilledWaterVO2Merge> getBilledWatersToMerge(){
		return baseMapper.selectList(null, "EcmMzfWaterMapper.getBilledWatersToMerge");
	}

	
}
