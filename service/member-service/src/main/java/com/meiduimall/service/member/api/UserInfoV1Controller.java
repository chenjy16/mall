package com.meiduimall.service.member.api;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.service.member.constant.ApiStatusConst;
import com.meiduimall.service.member.model.MSMembersGet;
import com.meiduimall.service.member.model.request.RequestGetMemberBasicInfo;
import com.meiduimall.service.member.service.UserInfoService;

/**
 * 获取用户基本信息
 * @author chencong
 *
 */
@RestController
@RequestMapping("/member/member_service/v1")
public class UserInfoV1Controller{
	
	private final static Logger logger=LoggerFactory.getLogger(UserInfoV1Controller.class);
	
	@Autowired
	private UserInfoService userInfoService;
	
	/**根据memId获取会员基本信息*/
	@GetMapping(value = "/get_member_basic_info")
	ResBodyData getmemberbasicinfo(@Valid RequestGetMemberBasicInfo requestGetMemberBasicInfo) {
		String memId=requestGetMemberBasicInfo.getMemId();
		logger.info("收到会员：{}获取基本信息API请求",memId);
		try {
			ResBodyData resBodyData = userInfoService.getBasicInfoByMemId(memId);
			logger.info("获取会员：{}基本信息API请求结果  ：{}",memId,resBodyData.toString());
			return resBodyData;
		} catch (Exception e) {
			logger.error("获取会员基本信息API请求异常：{}",e.toString());
			throw new ApiException(ApiStatusConst.GET_USERINFO_EXCEPTION,ApiStatusConst.getZhMsg(ApiStatusConst.GET_USERINFO_EXCEPTION));
		}
	}
	
	
	/**
	 * 保存当前会员基本信息 http://IP:PORT/Authorized/SaveMemberInfo
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	/*@RequestMapping(value = "/savememberbasicinfo",method=RequestMethod.POST)
	public void register() throws Exception {
		JSONObject json = new JSONObject();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONObject param = HttpClientUtil.readStreamToJsonObject(request);
			//从流中取参数
			String token = param.getString("token");
			String mem_sex = param.getString("mem_sex");
			String mem_birthday = param.getString("mem_birthday");
			String mem_address_area = param.getString("mem_address_area");
			String mem_address = param.getString("mem_address");
			String mem_pic = param.getString("mem_pic");
			String nick_name = param.getString("nick_name");
			json = userInfoService.saveMemberBasicInfo(token, mem_sex, mem_birthday, mem_address_area, mem_address, mem_pic, nick_name);
		} catch (Exception e) {
			try {
				out = response.getWriter();
				json.put(SysParamsConst.STATUS, "9999");
				json.put(SysParamsConst.MSG, "服务器错误!");
			} catch (IOException e1) {
				logger.error("服务器错误:{}", e1.getMessage());
			}
		}
		out.print(json.toString());
	}*/
	
}
