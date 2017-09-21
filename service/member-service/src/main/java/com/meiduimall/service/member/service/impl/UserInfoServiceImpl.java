package com.meiduimall.service.member.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.redis.util.RedisTemplate;
import com.meiduimall.service.member.config.ServiceUrlProfileConfig;
import com.meiduimall.service.member.constant.ApiStatusConst;
import com.meiduimall.service.member.constant.SysParamsConst;
import com.meiduimall.service.member.dao.BaseDao;
import com.meiduimall.service.member.model.MSMemberAddresses;
import com.meiduimall.service.member.model.MSMembersGet;
import com.meiduimall.service.member.model.MSMembersSet;
import com.meiduimall.service.member.model.MemberAddressesSet;
import com.meiduimall.service.member.model.response.ResponseMemberBasicInfo;
import com.meiduimall.service.member.service.MoneyService;
import com.meiduimall.service.member.service.PointsService;
import com.meiduimall.service.member.service.UserInfoService;
import com.meiduimall.service.member.util.DESC;
import com.meiduimall.service.member.util.DateUtil;
import com.meiduimall.service.member.util.HttpUtils;
import com.meiduimall.service.member.util.StringUtil;

/**
 * 会员信息接口实现类
 * @author chencong
 *
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
	
	private final static Logger logger=LoggerFactory.getLogger(UserInfoServiceImpl.class);
	
	@Autowired
	BaseDao baseDao;
	
	@Autowired
	PointsService pointsService;
	
	@Autowired
	MoneyService moneyService;
	
	@Autowired
	ServiceUrlProfileConfig serviceUrlProfileConfig;

	@Override
	public ResBodyData getBasicInfoByMemId(String memId) {
		ResBodyData resBodyData=new ResBodyData(ApiStatusConst.SUCCESS,ApiStatusConst.getZhMsg(ApiStatusConst.SUCCESS));
		ResponseMemberBasicInfo memberBasicInfo=baseDao.selectOne(memId,"MSMembersMapper.getRespMemberBasicInfoByMemId");//根据memId查询会员基本信息
		if(memberBasicInfo==null){//如果不存在这个会员
			throw new ServiceException(ApiStatusConst.MEMBER_NOT_EXIST);
		}

		MSMemberAddresses addresses=baseDao.selectOne(memId,"MSMemberAddressesMapper.getMemberAddressByMemId");//根据memId查询会员详细地址
		if(addresses!=null){
			if(null!=addresses.getDictIdProvince()&&null!=addresses.getDictIdCity()&&null!=addresses.getDictIdArea()){//添加省市区地址信息，分号隔开
				StringBuffer addressShengShiQu = new StringBuffer();
				addressShengShiQu.append(baseDao.selectOne(addresses.getDictIdProvince().toString(),"MSCityMapper.getNameByNo").toString());
				addressShengShiQu.append(";");
				addressShengShiQu.append(baseDao.selectOne(addresses.getDictIdCity().toString(),"MSCityMapper.getNameByNo").toString());
				addressShengShiQu.append(";");
				addressShengShiQu.append(baseDao.selectOne(addresses.getDictIdArea().toString(),"MSCityMapper.getNameByNo").toString());
				memberBasicInfo.setMemAddressShengShiQu(addressShengShiQu.toString());
			}
		}
		/**调用账户服务>根据memId查询是否存在支付密码*/
		String result=null;
		try {
			result=HttpUtils.get(serviceUrlProfileConfig.getAccountServiceUrl()+"/member/account_service/v1/is_exist_paypwd?memId="+memId);
		} catch (Exception e) {
			logger.error("调用账户服务http请求异常：{}",e.toString());
		}
		JSONObject j=JSONObject.parseObject(result);
		
		/**会员基本信息添加是否设置支付密码和支付密码开关状态*/
		memberBasicInfo.setPaypwd_isopen("Y".equals(memberBasicInfo.getPaypwd_isopen())?"1":"0");
		if(!"0".equals(j.getString(SysParamsConst.STATUS)))
			memberBasicInfo.setPaypwd_isset("0");
		memberBasicInfo.setPaypwd_isset("1");
		
		/**会员基本信息添加积分总额（包含冻结解冻的积分）和余额总额*/
		memberBasicInfo.setTotalmoney(moneyService.getTotalMoney(memId));
		memberBasicInfo.setTotalpoints(pointsService.getTotalPoints(memId,memberBasicInfo.getCurrentpoints()));
		
		resBodyData.setData(memberBasicInfo);
		return resBodyData;
	}


	@Override
	public JSONObject getMemberInfoByPhone(String phone) throws Exception{
		JSONObject json = new JSONObject();
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		Map<String, String> resultMap = new HashMap<String, String>();
		if (StringUtil.checkStr(phone)) {
			if (StringUtil.isPhoneToRegex(phone)) {
				String memId = baseDao.selectOne(DESC.encryption(phone), "MSMembersMapper.getMemberInfoByCondition");
				if (StringUtil.checkStr(memId)) {
					MSMembersGet member = baseDao.selectOne(memId, "MSMembersMapper.getMembersInfoByMemId");
					resultMap.put("nick_name", StringUtil.checkStr(member.getMemNickName()) == true ? member.getMemNickName() : "");
					resultMap.put("phone", StringUtil.checkStr(member.getMemPhone()) == true ? member.getMemPhone() : "");
					resultMap.put("login_name", StringUtil.checkStr(member.getMemLoginName()) == true ? member.getMemLoginName() : "");
					resultMap.put("pic_url", StringUtil.checkStr(member.getMemPic()) == true ? member.getMemPic() : "");
					json.put(SysParamsConst.STATUS, "0");
					json.put(SysParamsConst.MSG, "Success");
					result.add(resultMap);
					json.put(SysParamsConst.DATA, result);
				} else {
					json.put(SysParamsConst.STATUS, "1020");
					json.put(SysParamsConst.MSG, "当前会员不存在!");
					logger.info("当前会员ID:{}不存在!", memId);
				}
			} else {
				json.put(SysParamsConst.STATUS,"999");
				logger.info("手机号码:{}错误!", phone);
			}
		} else {
			json.put(SysParamsConst.STATUS,"999");
			logger.info("手机号码:{}错误!", phone);
		}
		return json;
	}

	@Transactional
	@Override
	public JSONObject saveMemberBasicInfo(String token, String mem_sex, String mem_birthday, String mem_address_area, String mem_address, String mem_pic, String nick_name) throws Exception {
		JSONObject json = new JSONObject();
		String memId = RedisTemplate.getJedisInstance().execGetFromCache(token);
		if (StringUtil.checkStr(memId)) {
			MSMembersSet member = new MSMembersSet();
			member.setMemId(memId);
			MemberAddressesSet address = new MemberAddressesSet();
			address.setMemId(memId);
			if (StringUtil.checkStr(mem_sex))
				member.setMemSex(mem_sex);
			if (StringUtil.checkStr(mem_birthday))
				member.setMemBirthday(new SimpleDateFormat(DateUtil.YYYY_MM_DD).parse(mem_birthday));
			if (StringUtil.checkStr(mem_pic))
				member.setMemPic(mem_pic);
			if (StringUtil.checkStr(nick_name))
				member.setMemNickName(nick_name);
			if (StringUtil.checkStr(mem_address_area) || StringUtil.checkStr(mem_address)) {
				String[] memAddress = mem_address_area.split("[;]");
				if (memAddress.length != 3) {
					json.put(SysParamsConst.STATUS, "9998");
					json.put(SysParamsConst.MSG, "省市区参数有误!");
					/*Logger.info("修改会员信息省市区参数有误:%s!", mem_address_area);*/
					return json;
				} else {
					String province = baseDao.selectOne(memAddress[0], "MSCityMapper.getCityIdByAddressName");
					String city = baseDao.selectOne(memAddress[1], "MSCityMapper.getCityIdByAddressName");
					String area = baseDao.selectOne(memAddress[2], "MSCityMapper.getCityIdByAddressName");
					if (StringUtil.checkStr(province))
						address.setCityIdProvince(Integer.valueOf(province));
					if (StringUtil.checkStr(city))
						address.setCityIdCity(Integer.valueOf(city));
					if (StringUtil.checkStr(area))
						address.setCityIdArea(Integer.valueOf(area));
				}
				if (StringUtil.checkStr(mem_address))
					address.setMemaDetails(mem_address);
				/*MemberAddressesGet memberAddressInfo = baseDao.selectOne(memId, "MSMemberAddressesMapper.getMemberAddressByMemId");
				if (StringUtil.checkObj(memberAddressInfo)) {
					baseDao.update(address, "MSMemberAddressesMapper.updateMemberAddressInfoByMemaId");
				} else {
					json.put(SysParaNameConst.STATUS, "9998");
					json.put(SysParaNameConst.MSG, "该用户不存在相关地址的数据!");
					Logger.info("该用户不存在相关地址的数据");
					return json;
				}*/
			}
			baseDao.update(member, "MSMembersMapper.updateMemberInfoByMemId");
			json.put(SysParamsConst.STATUS, "0");
			json.put(SysParamsConst.MSG, "Success");
		} else {
			json.put(SysParamsConst.STATUS, "1020");
			json.put(SysParamsConst.MSG, "当前会员不存在!");
			logger.info("当前会员ID:{}不存在!", memId);
		}
		return json;
	}
	
}
