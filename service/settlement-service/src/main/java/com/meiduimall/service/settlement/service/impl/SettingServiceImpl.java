package com.meiduimall.service.settlement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meiduimall.service.settlement.dao.BaseMapper;
import com.meiduimall.service.settlement.model.EcmSystemSetting;
import com.meiduimall.service.settlement.service.SettingService;

@Service
public class SettingServiceImpl implements SettingService {
	
	@Autowired
	private BaseMapper baseMapper;

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public EcmSystemSetting updatesystemsetting(EcmSystemSetting input)  {
		baseMapper.update(input, "EcmSystemSettingMapper.updatesystemsetting");
		return baseMapper.selectOne(input.getScode(), "EcmSystemSettingMapper.querysystemsetting");
	}

	@Override
	public List<EcmSystemSetting> listsystemsetting(EcmSystemSetting input)  {
		return baseMapper.selectList(input, "EcmSystemSettingMapper.listsystemsetting");
	}
	
}
