package com.meiduimall.service.settlement.service;

import java.util.Collection;

import com.meiduimall.service.settlement.model.EcmMzfOrderStatus;


/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: OrderStatusService.java
 * Author:   许彦雄
 * Date:     2017年3月14日 下午3:37:58
 * Description: 订单状态变更服务
 */
public interface OrderStatusService {
	
	/**
	 * 功能描述:  更新是否分润成功状态
	 * Author: 许彦 雄
	 * Date:   2017年3月14日 下午3:38:26
	 * @param  orderSn 订单号
	 * @return boolean
	 * 
	 */
	public boolean updateShareStatus(String orderSn);
	
	/**
	 * 更新订单积分是否成功送出
	 * @param  orderSn 订单号
	 * @return boolean
	 */
	public boolean updateScoreStatus(String orderSn);
	
	/**
	 * 功能描述:  更新账单是否创建成功状态
	 * Author: 许彦雄
	 * Date:   2017年3月14日 下午3:38:26
	 * @param  orderStatus 订单状态
	 * @return boolean
	 * 
	 */
	public boolean updateBillStatus(EcmMzfOrderStatus orderStatus);
	
	/**
	 * 功能描述:  更新一级推荐人1%现金余额是否成功送出状态
	 * Author: 许彦雄
	 * Date:   2017年3月14日 下午3:38:26
	 * @param  orderSn 订单号
	 * @return boolean
	 * 
	 */
	public boolean updateCashStatus(String orderSn);
	
	
	/**
	 * 功能描述:  批量更新一级推荐人1%现金余额是否成功送出状态
	 * Author: 许彦雄
	 * Date:   2017年3月14日 下午3:38:26
	 * @param  orderSns 订单号
	 * @return boolean
	 * 
	 */
	//注意：好像如果orderSns.size()>5000, in update 语句好像或报错。将来最好把orderSns进行分割，使每次orderSns小于5000
	public boolean batchUpdCashStatus(Collection<String> orderSns);
	

	
}
