package com.meiduimall.service.member.api;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.service.member.constant.SysParamsConst;
import com.meiduimall.service.member.service.ShareMenService;

/**
 * 推荐人相关接口
 * @author chencong
 *
 */
@RestController
@RequestMapping("/member/member_service/v1")
public class ShareMenV1Controller {
	
	private final static Logger logger=LoggerFactory.getLogger(ShareMenV1Controller.class);
	
	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private ShareMenService smfs;
	
	/**
	 * 获取会员二级推荐人接口 http://IP:PORT/Authorized/querySecondLevelShareMem
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/get_twolevel_sharemen",method=RequestMethod.GET)
	public void getsecondlevelsharemen(String memId) throws Exception {		
		JSONObject json = new JSONObject();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			json = smfs.queryShareMan(memId);
		} catch (Exception e) {
			try {
				out = response.getWriter();
				json.put(SysParamsConst.STATUS, "9999");
				json.put(SysParamsConst.MSG, "服务器错误!");
				logger.error("服务器错误:{}", e.getMessage());
			} catch (IOException e1) {
				logger.error("服务器错误:{}", e1.getMessage());
			}
		}
		out.print(json.toString());
	}
	
	
}