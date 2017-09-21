package com.meiduimall.service.catalog.service;

import com.meiduimall.service.catalog.entity.SysuserAccount;

/**
 * 用户相关
 * 
 * @author yangchang
 *
 */
public interface UserService {

	/**
	 * 根据memId，获取用户的账户信息
	 * 
	 * @param memId
	 *            用户的会员系统ID
	 * @return SysuserAccount对象
	 */
	SysuserAccount getUserByMemId(String memId);
}
