package com.meiduimall.service.settlement.service;

import java.util.List;

import com.meiduimall.service.settlement.model.EcmMzfShareProfit;

/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: MemberService.java
 * Author:   不详
 * Date:     2017年3月14日 下午3:37:58
 * Description: 会员系统送积分或现金奖励服务
 */
public interface MemberService {
	
	/**
	 * 功能描述:  更新用户积分（对接的壹购物那边的）
	 * Author: 许彦雄
	 * Date:   2016年12月14日 下午3:38:26 
	 * @param  phone 电话号码
	 * @param  credit 积分
	 * @param  source 来源
	 * @param  orderId 订单号
	 * @return boolean
	 */
    public boolean addConsumePoints(String phone,String credit,String source,String orderId);

	/**
	 * 功能描述:  给会员系统送积分
	 * Author: 许彦雄
	 * Date:   2017年2月20日 下午3:38:26
	 * @param  shareProfit 订单分润结果信息
	 * @return String
	 */
    public List<String> sendScore(EcmMzfShareProfit shareProfit);

	/**
	 * 功能描述:  更新一级推荐人1%现金余额到会员系统
	 * Author: 许彦雄
	 * Date:   2017年2月20日 下午3:38:26
	 */
    public void updateReferrerCash();
}
