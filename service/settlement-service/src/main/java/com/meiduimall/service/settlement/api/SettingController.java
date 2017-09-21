package com.meiduimall.service.settlement.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.settlement.common.SettlementUtil;
import com.meiduimall.service.settlement.model.EcmSystemSetting;
import com.meiduimall.service.settlement.service.SettingService;

/**
 * Copyright (C), 2002-2017, 美兑壹购物
 * FileName: SettingController.java
 * Author:   许彦雄
 * Date:     2017年3月14日 下午3:37:58
 * Description: 结算服务参数配置和查询接口
 */
@RestController
@RequestMapping("/settlementservice/settingservice/v1")
public class SettingController {
	
	@Autowired
	private SettingService settingService;

	/**
	 * 功能描述:  更新分润比例配置接口
	 * Author: 许彦 雄
	 * Date:   2017年3月14日 下午3:38:26   
	 * @param  systemSetting 系统设置相关信息
	 * @return ResBodyData
	 * 
	 */
	@PostMapping(value="/updatesystemsetting")
	public ResBodyData updatesystemsetting(@Validated EcmSystemSetting systemSetting) {
		EcmSystemSetting ecmSystemSetting = settingService.updatesystemsetting(systemSetting);
		return SettlementUtil.success(ecmSystemSetting);
	}
	

	/**
	 * 功能描述:  查询分润比例配置列表接口
	 * Author: 许彦 雄
	 * Date:   2017年3月14日 下午3:38:26   
	 * @param  systemSetting 系统设置相关信息
	 * @return ResBodyData
	 * 
	 */
	@PostMapping(value="/listsystemsetting")
	public ResBodyData listsystemsetting(EcmSystemSetting systemSetting){
		List<EcmSystemSetting> systemSettingList = settingService.listsystemsetting(systemSetting);
		return SettlementUtil.success(systemSettingList);
	 }
	
}
