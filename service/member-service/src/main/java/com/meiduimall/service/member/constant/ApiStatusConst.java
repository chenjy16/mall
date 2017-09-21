package com.meiduimall.service.member.constant;

import com.meiduimall.core.BaseApiCode;

/**
 * API返回状态编码和编码语义
 * @author chencong
 *
 */
public abstract class ApiStatusConst extends BaseApiCode {
	
    /**公共*/
	public final static Integer REQUIRED_PARAM_EMPTY= 1002;
	public final static Integer MD5_EXCEPTION= 1003;
	public final static Integer DECRYPTION_EXCEPTION= 1004;
	public final static Integer ENCRYPTION_EXCEPTION= 1005;
	public final static Integer HTTP_EXCEPTION= 1006;
	public final static Integer PARSE_DATE_EXCEPTION= 1007;
	public final static Integer DB_SELECT_EXCEPTION= 1008;
	public final static Integer DB_UPDATE_EXCEPTION= 1009;
	public final static Integer DB_DELETE_EXCEPTION= 1010;
	public final static Integer DB_INSERT_EXCEPTION= 1011;
	
	public final static Integer USERNAME_ERROR=8001;
	public final static Integer MEMBER_NOT_EXIST=8002;
	public final static Integer PASSWORD_ERROR=8003;
	public final static Integer TOKEN_ERROR=8004;
	public final static Integer PASSWORD_OR_USERNAME_ERROR=8005;
	public final static Integer PHONE_ALREADY_REGISTED=8006;
	public final static Integer LOGINNAME_ALREADY_REGISTED=8007;
	public final static Integer SHARE_MAN_CANNOT_ITSELF=8008;
	public final static Integer SHARE_MAN_NOT_EXIST=8009;
	public final static Integer MEMBER_FORBIDDEN=8011;
	public final static Integer MEMBER_LOCK=8012;
	
	public final static Integer GET_USERINFO_EXCEPTION=8012;
	public final static Integer SET_PAYPWD_STATUS_EXCEPTION=8013;
	public final static Integer LOGIN_EXCEPTION=8014;
	
	static {
		zhMsgMap.put(REQUIRED_PARAM_EMPTY, "必填参数为空");
		zhMsgMap.put(MD5_EXCEPTION, "生成MD5程序异常");
		zhMsgMap.put(DECRYPTION_EXCEPTION, "解密程序异常");
		zhMsgMap.put(ENCRYPTION_EXCEPTION, "加密程序异常");
		zhMsgMap.put(HTTP_EXCEPTION, "HTTP请求异常");
		zhMsgMap.put(PARSE_DATE_EXCEPTION, "日期解析异常");
		zhMsgMap.put(DB_SELECT_EXCEPTION, "数据库查询失败");
		zhMsgMap.put(DB_UPDATE_EXCEPTION, "数据库更新失败");
		zhMsgMap.put(DB_DELETE_EXCEPTION, "数据库删除失败");
		zhMsgMap.put(DB_INSERT_EXCEPTION, "数据库插入失败");
		
		zhMsgMap.put(USERNAME_ERROR, "用户名输入错误");
		zhMsgMap.put(MEMBER_NOT_EXIST, "当前用户不存在");
		zhMsgMap.put(PASSWORD_ERROR, "密码输入错误");
		zhMsgMap.put(TOKEN_ERROR, "用户令牌错误");
		zhMsgMap.put(PASSWORD_OR_USERNAME_ERROR, "用户或密码输入错误");
		zhMsgMap.put(PHONE_ALREADY_REGISTED, "该手机号已经被注册");
		zhMsgMap.put(LOGINNAME_ALREADY_REGISTED, "该用户名已经被注册");
		zhMsgMap.put(SHARE_MAN_CANNOT_ITSELF, "推荐人不能是自己");
		zhMsgMap.put(SHARE_MAN_NOT_EXIST, "您输入的推荐人不存在");
		zhMsgMap.put(MEMBER_LOCK, "密码输入错误次数过多已被锁定，明天0点0分自动解除");
		zhMsgMap.put(MEMBER_FORBIDDEN, "会员账号已被禁用");
		
		zhMsgMap.put(GET_USERINFO_EXCEPTION, "获取用户信息程序异常");
		zhMsgMap.put(SET_PAYPWD_STATUS_EXCEPTION, "设置支付密码开关程序异常");
		zhMsgMap.put(LOGIN_EXCEPTION, "登录程序异常");
	}

}
