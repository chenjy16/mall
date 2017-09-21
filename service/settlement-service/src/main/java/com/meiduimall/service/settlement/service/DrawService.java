package com.meiduimall.service.settlement.service;

import java.util.List;
import java.util.Map;

import com.meiduimall.service.settlement.model.EcmMzfDraw;
import com.meiduimall.service.settlement.model.EcmMzfDrawWater;

/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: DrawService.java
 * Author:   guidl
 * Date:     2017年3月24日 上午11:25:02
 * Description: 提现相关
 */
public interface DrawService {

	
	/**
	 * 功能描述:  获取区代、个代或商家可提现金额
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02
	 * @param  code 代理编号
	 * @return Map
	 */
	public Map<String, Object> queryAccoutBalance(String code);

	
	/**
	 * 功能描述:  获取提现管理列表
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02
	 * @param  params 条件参数
	 * @return EcmMzfDraw
	 */
	public List<EcmMzfDraw> queryDrawCash(Map<String, Object> params);
	

	/**
	 * 功能描述:  获取提现管理列表总数
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02
	 * @param  params 条件参数
	 * @return int
	 */
	public int getDrawCount(Map<String,Object> params);


	/**
	 * 功能描述:  根据提现编号获取提现详情
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02
	 * @param  drawCode 提现编号
	 * @return EcmMzfDraw
	 */
	public EcmMzfDraw queryDrawCashById(String drawCode);

	
	/**
	 * 功能描述:  审核提现申请
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02
	 * @param  ecmmzfdraw 提现相关信息
	 * @return Map
	 */
	public Map<String, Object> verifyDrawCashById(EcmMzfDraw ecmmzfdraw);

	
	/**
	 * 功能描述:  驳回提现申请
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02
	 * @param  ecmmzfdrawinput 提现相关信息
	 * @return Map 
	 */
	public Map<String, Object> rejectDrawCashById(EcmMzfDraw ecmmzfdrawinput);

	
	/**
	 * 功能描述:  确认提现转账成功或失败（更改提现状态）
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02
	 * @param  ecmmzfdrawinput 提现相关信息
	 * @return Map
	 */
	public Map<String, Object> confirmDrawCashByIdByType(EcmMzfDraw ecmmzfdrawinput);
	

	/**
	 * 功能描述:  插入提现申请相关表信息
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02
	 * @param  ecmMzfDraw 提现相关信息
	 * @return boolean 
	 */
	public boolean insertDrawInfo(EcmMzfDraw ecmMzfDraw);
	
	
	/**
	 * 功能描述:  插入提现申请记录
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02
	 * @param  ecmMzfDraw 提现相关信息
	 * @return int  
	 */
	public int insertDraw(EcmMzfDraw ecmMzfDraw);
 
	
	/**
	 * 功能描述:  插入提现流水
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02
	 * @param  ecmMzfDrawWater 提现流水信息
	 * @return int 
	 */
	public int insertDrawWater(EcmMzfDrawWater ecmMzfDrawWater);
	
	
	/**
	 * 功能描述:  根据code、nowTime查询提现流水表ecm_mzf_draw_water中的总数，用于生成提现流水编号
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02
	 * @param  params(code、nowTime)
	 * @return int  
	 */
	public int getDrawWaterCount(Map<String, Object> params);
	

	/**
	 * 功能描述:  获取当天提现记录总数
	 * Author: guidl
	 * Date:   2017年3月24日 上午11:25:02
	 * @param  params 条件参数
	 * @return int  
	 */
	public int getCountByCode(Map<String,Object> params);
	
	
}
