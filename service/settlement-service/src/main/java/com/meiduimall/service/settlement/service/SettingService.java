package com.meiduimall.service.settlement.service;

import java.util.List;

import com.meiduimall.service.settlement.model.EcmSystemSetting;

/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: SettingService.java
 * Author:   许彦雄
 * Date:     2017年3月14日 下午3:37:58
 * Description: 结算服务参数配置和查询服务
 */
public interface SettingService {

	/**
	 * 功能描述:  更新分润比例配置接口
	 * Author: 许彦 雄
	 * Date:   2017年3月14日 下午3:38:26
	 * @param  systemSetting 系统设置信息
	 * @return EcmSystemSetting
	 */
	public EcmSystemSetting updatesystemsetting(EcmSystemSetting systemSetting);

	
	/**
	 * 功能描述:  查询分润比例配置列表接口
	 * Author: 许彦 雄
	 * Date:   2017年3月14日 下午3:38:26
	 * @param  systemSetting 系统设置信息
	 * @return EcmSystemSetting
	 */
	public List<EcmSystemSetting> listsystemsetting(EcmSystemSetting systemSetting);
	
}
