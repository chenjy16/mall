package com.meiduimall.service.member.api;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.service.member.constant.SysParamsConst;
import com.meiduimall.service.member.service.FunsService;

/**
 * 粉丝相关接口
 * @author chencong
 *
 */
@RestController
@RequestMapping("/member/member_service/v1")
public class FunsV1Controller {
	
	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private FunsService smfs;
	
	
	/**
	 * 粉丝明细接口 http://IP:PORT/ AuthorizationMembers/fansDetailList
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/funs_detail_list",method=RequestMethod.GET)
	public void funsdetaillist(String memId, String levelNum, int limit, int pageNo) throws Exception {		
		JSONObject json = new JSONObject();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			json = smfs.queryFansDetailsList(memId, levelNum, limit, pageNo);
		} catch (Exception e) {
			try {
				out = response.getWriter();
				json.put(SysParamsConst.STATUS, "9999");
				json.put(SysParamsConst.MSG, "服务器错误!");
			} catch (IOException e1) {
			}
		}
		out.print(json.toString());
	}
	
	/**
	 * 粉丝数量接口 http://IP:PORT/AuthorizationMembers/funscountbylevel
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/funs_count_group_level",method=RequestMethod.GET)
	public void funscountbylevel(String memId) throws Exception {		
		JSONObject json = new JSONObject();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			json = smfs.queryFansNumber(memId);
		} catch (Exception e) {
			try {
				out = response.getWriter();
				json.put(SysParamsConst.STATUS, "9999");
				json.put(SysParamsConst.MSG, "服务器错误!");
			} catch (IOException e1) {
			}
		}
		out.print(json.toString());
	}
}