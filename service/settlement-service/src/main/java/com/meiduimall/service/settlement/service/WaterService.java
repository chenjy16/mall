package com.meiduimall.service.settlement.service;

import java.util.List;
import java.util.Map;

import com.meiduimall.service.settlement.vo.BilledWaterVO2Merge;


/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: WaterService.java
 * Author:   guidl
 * Date:     2017年3月24日 下午14:14:28
 * Description: 流水管理
 */
public interface WaterService {

	
	/**
	 * 功能描述:  根据流水编号获取提现流水详情（主要用于提现的流水详情）
	 * Author: guidl
	 * Date:   2017年3月24日 下午14:14:28 
	 * @param  waterId 流水编号
	 * @param  waterType 流水类型
	 * @return Map
	 */
	public Map<String,Object> getWaterType1Detail(String waterId, String waterType) ;
	
	
	/**
	 * 功能描述:  获取流水详情（主要用于代理、保证金的流水详情）
	 * Author: guidl
	 * Date:   2017年3月24日 下午14:14:28
	 * @param  waterId 流水编号
	 * @param  waterType 流水类型
	 * @return Map
	 */
	public Map<String,Object> getWaterDetail(String waterId, String waterType) ;
	
	
	/**
	 * 功能描述:  合并流水表中同一用户重复流水金额
	 * Author: 许彦 雄
	 * Date:   2017年3月24日 下午14:14:28
	 * @return BilledWaterVO2Merge
	 */
	public List<BilledWaterVO2Merge> getBilledWatersToMerge() ;
	
	
}
