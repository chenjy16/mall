package com.meiduimall.service.member.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meiduimall.core.Constants;
import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.exception.SystemException;
import com.meiduimall.redis.util.RedisTemplate;
import com.meiduimall.service.member.constant.ApiStatusConst;
import com.meiduimall.service.member.constant.SysEncrypParamsConst;
import com.meiduimall.service.member.constant.SysParamsConst;
import com.meiduimall.service.member.dao.BaseDao;
import com.meiduimall.service.member.model.MSConsumePointsDetail;
import com.meiduimall.service.member.model.MSMemLoginLog;
import com.meiduimall.service.member.model.MSMemberBasicAccount;
import com.meiduimall.service.member.model.MSMemberCertificate;
import com.meiduimall.service.member.model.MSMemberRole;
import com.meiduimall.service.member.model.MSMembersGet;
import com.meiduimall.service.member.model.MSMembersSet;
import com.meiduimall.service.member.model.MemberAddressesSet;
import com.meiduimall.service.member.model.request.RequestLogin;
import com.meiduimall.service.member.model.response.ResponseMemberBasicInfo;
import com.meiduimall.service.member.service.BasicOpService;
import com.meiduimall.service.member.util.DESC;
import com.meiduimall.service.member.util.DoubleCalculate;
import com.meiduimall.service.member.util.SerialStringUtil;
import com.meiduimall.service.member.util.StringUtil;
import com.meiduimall.service.member.util.ToolsUtil;


/**
 * 用户基本的操作实现类
 * @author chencong
 *
 */
@Service
public class BasicOpServiceImpl implements BasicOpService {
	
	private final static Logger logger=LoggerFactory.getLogger(BasicOpServiceImpl.class);

	@Autowired
	BaseDao baseDao;
	
	@Override
	public Map<String, Object> handlesignout(JSONObject jsonObject) throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();//返回结果
		String userid=jsonObject.getString("user_id");
		String memid=  baseDao.selectOne(DESC.encryption(userid),"MSMembersMapper.getMemberInfoByCondition");
		//如果不存在这个memid
		if(StringUtil.isEmptyByString(memid)){
			map.put("status_code", ApiStatusConst.MEMBER_NOT_EXIST);
			return map;
		}
		try {
			RedisTemplate rt=RedisTemplate.getJedisInstance();
			if(rt.execExistsFromCache(memid))
			{
				String token=rt.execGetFromCache(memid);
				if(rt.execExistsFromCache(token))
				{
					if(rt.execDelToCache(memid))
					{
						if(rt.execDelToCache(token))
						{
							map.put("status_code","0");
							map.put("result_msg","success");
						}
						else
						{
							map.put("status_code","1111");
							map.put("result_msg","token"+token+" 删除这个key失败");
						}
					}
					else
					{
						map.put("status_code","1111");
						map.put("result_msg","memid:"+memid+" 删除这个key失败");
					}
				}
				else
				{
					map.put("status_code","1111");
					map.put("result_msg","token:"+token+" 不存在这个key");
				}
			}
			else
			{
				map.put("status_code","1111");
				map.put("result_msg","memid:"+memid+"不存在这个key");
			}
		} catch (Exception e) {
			throw e;
		}
		return map;
	}

	
	@Override
	public Map<String, Object> getput(JSONObject jsonObject) throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();//返回结果
		String type=null;//操作类型，1是get，2是put
		/**判断type参数是否存在**/
		if(!jsonObject.containsKey("type"))
		{
			map.put("status_code","2222");
			map.put("result_msg","type参数错误");
			return map;
		}
		type=jsonObject.getString("type");
		/**判断type是否是1或2或3,1表示get，2表示put，3表示通过token找userid专用**/
		if(!"1".equals(type)&&!"2".equals(type)&&!"3".equals(type))
		{
			map.put("status_code","2222");
			map.put("result_msg","type参数错误");
			return map;
		}
		else {
			/**通过userid获取memid并判断该memid是否存在**/
			String memid=null;
			String userid=null;
			String token=null;
			if(jsonObject.containsKey("user_id"))
			{
				userid=jsonObject.getString("user_id");
				memid=  baseDao.selectOne(DESC.encryption(userid),"MSMembersMapper.getMemberInfoByCondition");
				//如果不存在这个memid
				if(StringUtil.isEmptyByString(memid)){
					map.put("status_code", ApiStatusConst.MEMBER_NOT_EXIST);
					return map;
				}	
			}
			if(jsonObject.containsKey("token"))
			{
				token=jsonObject.getString("token");
			}
			RedisTemplate rt=RedisTemplate.getJedisInstance();
			/**如果是get操作**/
			if("1".equals(type))
			{
				if(rt.execExistsFromCache(memid))
				{
					//通过memid(key)获取token
					token=rt.execGetFromCache(memid);
					map.put("status_code","0");
					map.put("result_msg","success");
					Map<String, Object> result_map=new HashMap<>();
					result_map.put("token",token);
					result_map.put("memId",memid);
					map.put("result",JSON.toJSON(result_map));
				}
				else
				{
					map.put("status_code","0");
					map.put("result_msg","redis不存在这个key:"+memid);
					Map<String, Object> result_map=new HashMap<>();
					result_map.put("token","");
					result_map.put("memId","");
					map.put("result",JSON.toJSON(result_map));
					return map;
				}
			}
			/**如果是put操作**/
			if("2".equals(type))
			{
				token=ToolsUtil.createToken(userid);
				//把token存储到redis
				RedisTemplate.getJedisInstance().execSetexToCache(token,Constants.REDIS_ONEMONTH,memid);
				//临时代码，兼容旧会员系统
				RedisTemplate.getJedisInstance().execSetexToCache(memid,Constants.REDIS_ONEMONTH,token);
				map.put("status_code","0");
				map.put("result_msg","success");
				Map<String, Object> result_map=new HashMap<>();
				result_map.put("token",token);
				result_map.put("memId",memid);
				map.put("result",JSON.toJSON(result_map));
			}
			/**如果是通过token获取user_id操作**/
			if("3".equals(type))
			{
				map.put("status_code","0");
				map.put("result_msg","success");
				Map<String, Object> result_map=new HashMap<>();
				if(rt.execExistsFromCache(token))
				{
					memid=rt.execGetFromCache(token);
					result_map.put("token",token);
					result_map.put("memId",memid);
				}
				else
				{
					result_map.put("token",token);
					result_map.put("memId","");
				}
				map.put("result",JSON.toJSON(result_map));
			}
		}
		return map;
	}
	
	
	public ResBodyData login(RequestLogin requestLogin) throws SystemException {
		ResBodyData resBodyData=new ResBodyData(ApiStatusConst.SUCCESS,ApiStatusConst.getZhMsg(ApiStatusConst.SUCCESS));

		String userid=requestLogin.getUser_name();
		String password=requestLogin.getPassword();
		
		Map<String, Object> mapCondition=new HashMap<>();//查询条件
		mapCondition.put("userid",DESC.encryption(userid));
		MSMembersGet msMembersGet=baseDao.selectOne(mapCondition,"MSMembersMapper.getMemberBasicInfoByCondition");//根据userid判断该用户是否存在
		if(msMembersGet==null){
			
		}
		
		/**判断这个用户是否由于登录失败次数过多被锁定*/
		String redisLoginFail=SysParamsConst.REDIS_LOGIN_LOCK+SysParamsConst.CONNECTION+msMembersGet.getMemId();
		if(RedisTemplate.getJedisInstance().execExistsFromCache(redisLoginFail)){
			String count=RedisTemplate.getJedisInstance().execGetFromCache(redisLoginFail);
			if(Integer.valueOf(count)>=SysParamsConst.MAX_LOGIN_FAIL_COUNT)
				throw new ServiceException(ApiStatusConst.MEMBER_LOCK,ApiStatusConst.getZhMsg(ApiStatusConst.MEMBER_LOCK));
				
		}
		
		/**根据userid和密码查询会员信息*/
		String[] ps=password.split(SysParamsConst.MD_PASSWORD_SPLIT_STR);//1GW会员密码和美兑会员密码用"-"隔开，第一个是1GW会员密码
		mapCondition.put("ygw_pwd",ps[0]);
		mapCondition.put("md_pwd",ps[0]);
		if(ps.length>=2)
			mapCondition.put("md_pwd",ps[1]);
		msMembersGet=baseDao.selectOne(mapCondition,"MSMembersMapper.getMemberBasicInfoByCondition");
		
		if(msMembersGet!=null){
			logger.info("会员：{}用户名和密码输入正确",userid);
			if(msMembersGet.getDictMemStatus().equals(SysEncrypParamsConst.MEMBER_FORBIDDEN_EN)){//如果该会员账号已被禁用
				logger.info("会员{}被禁用，无法登录！",userid);
				throw new ServiceException(ApiStatusConst.MEMBER_FORBIDDEN,ApiStatusConst.getZhMsg(ApiStatusConst.MEMBER_FORBIDDEN));
			}
			String redisToken=ToolsUtil.createToken(userid);
			logger.info("token创建成功：{}",redisToken);
			
			/**更新会员相关信息*/
			msMembersGet.setMemLicenseKey(redisToken);
			if(null!=msMembersGet.getMemLoginTime()){
				msMembersGet.setPfLastLoginTime(msMembersGet.getMemLoginTime());//设置上一次登录时间
			}
			msMembersGet.setMemLoginTime(new Date());
			msMembersGet.setDictMemStatus(SysEncrypParamsConst.MEMBER_STATUS_OK);
		    baseDao.update(msMembersGet,"MSMembersMapper.updateMemberBasicInfoByCondition");
			
		    RedisTemplate.getJedisInstance().execSetexToCache(redisToken,Constants.REDIS_ONEMONTH,msMembersGet.getMemId());//把token存储到redis，并设置失效时间一个月
		    RedisTemplate.getJedisInstance().execSetexToCache(msMembersGet.getMemId(),Constants.REDIS_ONEMONTH,redisToken);//临时代码，兼容旧会员系统
			
			Map<String, Object> mapData=new HashMap<>();
			mapData.put("user_name",msMembersGet.getMemLoginName());
			mapData.put("nickname",msMembersGet.getMemNickName());
			mapData.put("token",redisToken);
			mapData.put("portrait","");//临时代码，适配旧APP
			mapData.put("ry_token","");//临时代码，适配旧APP
			resBodyData.setData(mapData);
		}
		else{
			logger.info("会员：{}用户名或密码输入错误，登录失败",userid);
			resBodyData.setStatus(ApiStatusConst.PASSWORD_OR_USERNAME_ERROR);
			resBodyData.setMsg(ApiStatusConst.getZhMsg(ApiStatusConst.PASSWORD_OR_USERNAME_ERROR));
		
			if(RedisTemplate.getJedisInstance().execExistsFromCache(redisLoginFail)){//如果登录失败过
				String lockCount=RedisTemplate.getJedisInstance().execGetFromCache(redisLoginFail);//获取已登录失败的次数
				int newlockCount=Integer.valueOf(lockCount)+1;//登录失败次数+1
				int lockTime=ToolsUtil.getNowToTomorrowTimeSub(); //计算距离0点0分的秒数
				RedisTemplate.getJedisInstance().execSetexToCache(redisLoginFail,lockTime,String.valueOf(newlockCount));//更新失败次数，0点0分自动解除
			}
			else{//如果没有登录失败过，就设置一次
				RedisTemplate.getJedisInstance().execSetexToCache(redisLoginFail,Constants.REDIS_ONEDAY,SysParamsConst.REDIS_INIT_LOGIN_LOCK_COUNT);
			}
		}
		return resBodyData;
	}
	
	/**用户 注册
	 * @throws Exception */
	public Map<String, Object> register(JSONObject jsonObject) {
		Map<String, Object> map=new HashMap<String, Object>();
		boolean open_Share=true;//是否开启分享，默认开启

		String ip = jsonObject.getString("ip");
		String phone = jsonObject.getString("phone");//用户手机号
		//如果没有登录名，就分配一个
		String user_id=null;
		if(jsonObject.containsKey("user_id"))
		{
			user_id=jsonObject.getString("user_id");
		}
		else
		{
			String username_01="";
			username_01 = "1gw_"+phone+"0";
			for (int i = 0; i < 10; i++) {
				if(i > 0){
					user_id = username_01+i;
				}else{
					user_id = username_01;
				}
			}
		}
		String valid_code = jsonObject.getString("valid_code");//验证码
		String pass_word = jsonObject.getString("pass_word");//MD5过的密码
		String share_man = jsonObject.getString("share_man");//推荐人user_id
		String sign_source = jsonObject.getString("sign_source");//注册来源
		String register_type = jsonObject.getString("register_type");//注册类型
		
		/**校验验证码格式*/
		if (StringUtil.isEmptyByString(valid_code) || 6 != valid_code.length()) {
			logger.info("外部当前注册请求IP地址=" + ip + "结束,验证码错误");
			map.put(SysParamsConst.STATUS, ApiStatusConst.FAIL_BLACKLIST_VALIDATE);
			return map;
		}
		try {
			/**校验推荐人*/
			String share_id=null;//推荐人的memid
			if (!StringUtil.isEmptyByString(share_man)) {
				open_Share = false;
				if(user_id.equals(share_man))
				{
					logger.info("当前注册请求IP地址=" + ip + "结束，推荐人不能是自己");
					map.put(SysParamsConst.STATUS,ApiStatusConst.SHARE_MAN_CANNOT_ITSELF);
					return map;
				}
				share_id = validShareMan(DESC.encryption(share_man));
				if (StringUtil.isEmptyByString(share_id)) {
					logger.info("当前注册请求IP地址=" + ip + "结束，推荐人不存在");
					map.put(SysParamsConst.STATUS,ApiStatusConst.SHARE_MAN_NOT_EXIST);
					return map;
				}
			} else {
				open_Share = true;
				share_man = SysParamsConst.MD1GW_DEFAULT_SHARE_LOGIN_NAME;
				share_id = validShareMan(DESC.encryption(share_man));//系统默认推荐人
				logger.info("外部当前注册请求IP地址=" + ip + "，系统默认推荐人为："+share_man);
			}
			/**校验注册验证码*/
			String rk="";//注册验证码的key
			if(RedisTemplate.getJedisInstance().execExistsFromCache(rk))
			{
				String code=RedisTemplate.getJedisInstance().execGetFromCache(rk);
				if(!code.equals(valid_code))
				{
					logger.info("当前注册请求IP地址=" + ip + "结束，验证码错误");
					return map;
				}
			}
			else
			{
				logger.info("当前注册请求IP地址=" + ip + "结束，验证码不存在");
				return map;
			}
			/**校验手机号*/
			if(validUserId(DESC.encryption(phone)))
			{
				logger.info("当前注册请求IP地址=" + ip + "结束，该手机号已经被注册");
				return map;
			}
			/**校验用户名*/
			if(validUserId(DESC.encryption(user_id)))
			{
				logger.info("当前注册请求IP地址=" + ip + "结束，该用户名已经被注册");
				map.put(SysParamsConst.STATUS,ApiStatusConst.LOGINNAME_ALREADY_REGISTED);
				return map;
			}
			/**注册流程开始*/
			MSMembersSet memberSet=new MSMembersSet();//生成会员基本信息
			Date date = new Date();
			String memid=UUID.randomUUID().toString();
			memberSet.setMemId(memid);
			memberSet.setMemCreatedBy(memid);
			memberSet.setMemLoginName(user_id);
			memberSet.setMemLoginNameIsdefaultIschanged(jsonObject.containsKey("user_id")?"0_1":"1_0");//是否分配默认登录名
			memberSet.setMemOldPhone(phone);
			memberSet.setMemPhone(phone);
			memberSet.setMemLoginPwd(pass_word);
			memberSet.setMemNickName(phone);
			memberSet.setMemCreatedDate(date);
			memberSet.setMemCreatedCategory(1);// 创建
			memberSet.setDictMemStatus(SysEncrypParamsConst.MEMBER_STATUS_OK);//会员账号状态
			memberSet.setMemParentId(share_id);//推荐人memid
			if ("O2O".equals(sign_source)) {
				memberSet.setMemSignSource("1");
			}
			if ("1gw".equals(sign_source)) {
				memberSet.setMemSignSource("3");
			}
			if ("1".equals(register_type)) {//商家
				memberSet.setMemIsAllowShop(SysEncrypParamsConst.MEMBERKAIDIAN_YES);
			}
			if ("2".equals(register_type)) {//普通会员
				memberSet.setMemIsAllowShop(SysEncrypParamsConst.MEMBERKAIDIAN_NO);
			}
			memberSet.setMemBasicAccountTotal("0");//基本账户总额
			memberSet.setMemIsAllActivated(true);//是否所有绑定
			if(open_Share){
				memberSet.setMemLoginNameIsdefaultIschanged("1_0");//是否分配默认推荐人
			}else{
				memberSet.setMemLoginNameIsdefaultIschanged("0_1");	
			}
			//2、生成会员地址信息
			MemberAddressesSet ms = new MemberAddressesSet();
			ms.setMemaId(UUID.randomUUID().toString());
			ms.setMemId(memid);
			ms.setMemaStatus("0");
			//3、生成会员账户信息
			MSMemberBasicAccount mb = new MSMemberBasicAccount();
			mb.setMbaId(UUID.randomUUID().toString());
			mb.setMemId(memid);
			mb.setMbaTotalQuantity("0");//基本账户总额
			mb.setMbaCreatedDate(new Date());
			//4、生成会员证件信息
			MSMemberCertificate mc = new MSMemberCertificate();
			mc.setMcerId(UUID.randomUUID().toString());
			mc.setMemId(memid);
			mc.setDictMcerId(SysEncrypParamsConst.CERTIFICATE_SFZ);//会员证件类型
			mc.setMcStatus(SysEncrypParamsConst.HUIYUANRENZHEN_WRZ);//认证状态，默认未认证
			//5、生成会员角色信息
			MSMemberRole mro = new MSMemberRole();
			mro.setMemId(memid);
			mro.setRoleId(SysEncrypParamsConst.PUTONGHUIYUAN);
			//插入以上信息到数据库
			MSMemLoginLog ml = new MSMemLoginLog();
			if(insertMembserInfo(memberSet, ms, mb, mc, mro))
			{
				// 更新父类字符串 获取粉丝明细会用到
				/*baseDao.update(memberSet,"");*/
				//生成会员登录日志
				ml.setMemId(memid);
				ml.setMllogAction(SysParamsConst.REGISERTER);
				ml.setMllogContent("外部接口" + phone + "注册成功");
				ml.setMllogCreatedBy(memid);
				ml.setMllogCreatedDate(new Date());
				ml.setMllogId(UUID.randomUUID().toString());
				ml.setMllogIp(jsonObject.getString("ip"));
				ml.setMllogModule(SysParamsConst.LOGIN_WB);
				ml.setMllogRemark("外部接口" + phone + "注册成功");
				ml.setMllogStatus(SysParamsConst.REGISERTER);
			
			/**发送注册成功短信*/
			
			/**新注册成功用户增加100积分*/
			ResponseMemberBasicInfo memberBasicInfoDTO = baseDao.selectOne(memid,"MSMembersMapper.getRespMemberBasicInfoByMemId");
			if(memberBasicInfoDTO != null){
				//订单编号
				String orderId = "1GW+" + memberBasicInfoDTO.getLogin_name()+ "+" + String.valueOf(System.currentTimeMillis()/1000L);
				//注册人增加100积分
				int i=addMDConsumePoints(memberBasicInfoDTO,SysParamsConst.MD1GW_REGISTER_ADD_POINTS);
				if(i>0)
				{
					//写入增加积分日志
					addConsumePointDetail(memid,orderId,sign_source,SysParamsConst.MD1GW_REGISTER_ADD_POINTS,SysParamsConst.MD1GW_REGISTER_ADD_POINTS,phone,SysEncrypParamsConst.POINTS_OPERATOR_TYPE_ZCZS);
					//推荐人增加100积分
					if((!StringUtil.isEmptyByString(share_id))){
						//如果推荐人不是美兑
						if (!SysParamsConst.MD1GW_DEFAULT_SHARE_LOGIN_NAME.equals(share_man)) {
							ResponseMemberBasicInfo member_shareman = baseDao.selectOne(share_id,"MSMembersMapper.getRespMemberBasicInfoByMemId");
							//订单编号
							orderId = "1GW+" +share_man+ "+" + String.valueOf(System.currentTimeMillis()/1000L);
							//一级分享人增加100积分
							addMDConsumePoints(member_shareman,SysParamsConst.MD1GW_REGISTER_ADD_POINTS);
							//写入增加积分日志
							addConsumePointDetail(share_id,orderId,sign_source,SysParamsConst.MD1GW_REGISTER_ADD_POINTS,SysParamsConst.MD1GW_REGISTER_ADD_POINTS,phone,SysEncrypParamsConst.POINTS_OPERATOR_TYPE_YQZCZS);
							//发送分享人赠送积分短信
						}
					}
				}
			}
			//写入会员登录日志
			baseDao.insert(ml,"MSMemLoginLogMapper.insertMemberLoginLog");
			}
			
		} catch (Exception e) {
			logger.error("注册请求发生错误："+e.getMessage());
			map.put(SysParamsConst.STATUS,"9999");
			map.put(SysParamsConst.MSG,e.getMessage());
		}
		return map;
	}

	/**
	 * 用户退出
	 */
	public Map<String, Object> exit(JSONObject jsonObject) {
		return null;
	}

	/**
	 * APP打开时检查token是否有效
	 */
	@Override
	public Map<String, Object> checktoken(JSONObject jsonObject) {
		Map<String, Object> map=new HashMap<String, Object>();
		String token=null;//token
		try {
			if(!jsonObject.containsKey(SysParamsConst.TOKEN))
			{
				map.put(SysParamsConst.STATUS, ApiStatusConst.TOKEN_ERROR);
				return map;
			}
			//如果不存在token或者对应的值不对
			RedisTemplate rt=RedisTemplate.getJedisInstance();
			token=jsonObject.getString(SysParamsConst.TOKEN);
			if(!rt.execExistsFromCache(token))
			{
				map.put(SysParamsConst.STATUS, ApiStatusConst.TOKEN_ERROR);
				return map;
			}
			String memid=rt.execGetFromCache(token);
			if(StringUtil.isEmptyByString(memid))
			{
				map.put(SysParamsConst.STATUS, ApiStatusConst.TOKEN_ERROR);
				return map;
			}
			ResponseMemberBasicInfo memberBasicInfoDTO=baseDao.selectOne(memid,"MSMembersMapper.getRespMemberBasicInfoByMemId");
			if(memberBasicInfoDTO==null)
			{
				map.put(SysParamsConst.STATUS, ApiStatusConst.MEMBER_NOT_EXIST);
				return map;
			}
			Map<String, Object> result_map=new HashMap<>();
			result_map.put("user_id",memberBasicInfoDTO.getLogin_name());
			map.put(SysParamsConst.STATUS,"1");
			map.put(SysParamsConst.DATA,result_map); 
		} catch (Exception e) {
			logger.info("服务器错误{}",e.getMessage());
			map.put(SysParamsConst.MSG,e.getMessage());
		}
		return map;
	}
	
	/**
	 * 生成短信验证码
	 * @throws Exception 
	 */
    @Override
	public Map<String, Object> createValidateCode(JSONObject jsonObject) throws Exception {
		Map<String, Object> map=new HashMap<>();
		/*try {
			if(!jsonObject.containsKey("phone"))
			{
				map.put(SysParaNameConst.STATUS, ApiStatusConst.PHONE_ERROR);
				map.put(SysParaNameConst.MSG, ApiStatusConst.PHONE_ERROR_C);
				return map;
			}
			String type=jsonObject.getString("type");
			String url=SystemConfig.getInstance().configMap.get("Push_Service_Address");
			String code=ToolsUtil.getValidateCode();
			String code="123456";
			jsonObject.put(OauthConst.CLIENT_ID,"");//这个clientID是gateway分配给接入层的，不是接入层分配给前端APP的
			jsonObject.put(OauthConst.TIMESATAMP,System.currentTimeMillis());
			jsonObject.put("content",code);
			String sign=ToolsUtil.getSign(jsonObject);//注意此方法需要修改，缺参数
			jsonObject.put(OauthConst.SIGN,sign);
			Logger.info("请求获取验证码接口："+url+" 参数："+jsonObject.toString());
			String result=shortMessageService.getValidateCode(url,jsonObject.toString());
			JSONObject jresult=(JSONObject)JSONObject.parse(result);
			//上面需要判断短信是否发送成功
			if("0".contains(jresult.getString(SysParaNameConst.STATUS)))
			{
				switch (type) {
				case "1":
					JedisUtil.getJedisInstance().execSetexToCache(SysParaNameConst.REDISKEY_REGISTER_VALIDATE_CODE+jsonObject.containsKey("phone"),Constants.REDIS_ONEMONTH*3,code);
					break;

				default:
					break;
				}
			}
			switch (type) {
			case "1"://注册
				JedisUtil.getJedisInstance().execSetexToCache(SysParaNameConst.REDISKEY_REGISTER_VALIDATE_CODE+jsonObject.getString("phone"),Constants.REDIS_HALFMINUTE*2,code);
				break;
			case "6"://修改手机号
				JedisUtil.getJedisInstance().execSetexToCache(SysParaNameConst.UPDATE_PHONE_VALIDATE_CODE+jsonObject.getString("phone"),Constants.REDIS_HALFMINUTE*2,code);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			throw e;
		}
		map.put(SysParaNameConst.STATUS, ApiStatusConst.SUCCESS);
		map.put(SysParaNameConst.MSG, ApiStatusConst.SUCCESS_C);
		
		if(!jsonObject.containsKey("phone"))
		{
			map.put(SysParaNameConst.STATUS, ApiStatusConst.PHONE_ERROR);
			map.put(SysParaNameConst.MSG, ApiStatusConst.PHONE_ERROR_C);
			return map;
		}
		TaoBaoApiHelper helper = new TaoBaoApiHelper("http://gw.api.taobao.com/router/rest","23610521","1b6e3af2b15c01c4239e1d0f12481992");
		TaobaoHashMap map2 = new TaobaoHashMap();
		map.put("sms_type", CodeHelper.SMS_TYPE);
		map.put("sms_free_sign_name", CodeHelper.SMS_FREE_SIGN_NAME);
		map.put("sms_template_code", "SMS_13011423");
		map.put("rec_num",jsonObject.getString("phone"));																																															
		map.put("method",  CodeHelper.SEND_SMS_SERVICE_URL);
		map.put("sms_param", String.format(CodeHelper.CODE_PRODUCT, ToolsUtil.getValidateCode(),CodeHelper.SMS_FREE_SIGN_NAME));
		TaobaoResponse result=helper.execute(map2);*/
		return map;
	}
	
	/**校验是否有推荐人*/
	private final String  validShareMan(String share_man_id) throws Exception
	{
		String memid=null;
		try {
			memid=baseDao.selectOne(share_man_id,"MSMembersMapper.getMemberInfoByCondition");
		} catch (Exception e) {
			throw e;
		}
		return memid;
	}
	
	/**校验user_id是否已经被注册*/
	private final boolean validUserId(String phone) throws Exception
	{
		String memid=null;
		String pfid=null;
		try {
			memid=baseDao.selectOne(phone,"MSMembersMapper.getMemberInfoByCondition");//根据手机号查询会员表是否存在记录
			pfid=baseDao.selectOne(phone,"MSPlatFormsMapper.getPfRecordByUserId");//根据手机号查询是否是平台人员注册
			if(StringUtil.isEmptyByString(memid)&&StringUtil.isEmptyByString(pfid))
				return true;
		} catch (Exception e) {
			throw e;
		}
		return false;
	}
	
	/**插入会员基本信息以及相关联的信息*/
	@Transactional
	private final boolean insertMembserInfo(MSMembersSet msMembersSet,MemberAddressesSet memberAddressesSet,MSMemberBasicAccount account,MSMemberCertificate certificate,MSMemberRole role) throws Exception
	{
		try {
			int i1=baseDao.insert(msMembersSet,"");
			int i2=baseDao.insert(memberAddressesSet,"");
			int i3=baseDao.insert(account,"");
			int i4=baseDao.insert(certificate,"");
			int i5=baseDao.insert(role,"");
			if(i1>0&&i2>0&&i3>0&&i4>0&&i5>0)
			{
				return true;
			}
		} catch (Exception e) {
			throw e;
		}
		return false;
	}
	
	/**修改会员基本账户总额*/
	private final int addMDConsumePoints(ResponseMemberBasicInfo member, String consumePoints) throws Exception {
		int returnBool =0;
		try {
			//增加基本账户总额
			double addAtq = DoubleCalculate.add(
					Double.valueOf(member.getTotalpoints()),
					Double.valueOf(consumePoints));
			//修改会员基本账户总额
			Map<String,Object> map=new HashMap<>();
			map.put("memId",member.getMemId());
			map.put("addAtq",Double.toString(addAtq)); 
			returnBool =baseDao.update(map,"MSMembersMapper.updateAccountPointsByMemId");
		} catch (Exception e) {
			throw e;
		}
		return returnBool;
	}
	
	/**增加积分明细*/
	private final int addConsumePointDetail(String memid,String orderid,String ordersource,String consumepoint,String balance,String phone,String operatetype) throws Exception
	{
		int i=0;
		try {
			Date nowDate = new Date(System.currentTimeMillis());
			MSConsumePointsDetail consumePointsDetail=new MSConsumePointsDetail();
			consumePointsDetail.setMcpId(UUID.randomUUID().toString());
			consumePointsDetail.setMemId(memid);
			consumePointsDetail.setMcpOrderId(orderid);
			consumePointsDetail.setMcpOrderSource(SerialStringUtil.getDictOrderSource(ordersource));
			consumePointsDetail.setMcpIncome("0");
			consumePointsDetail.setMcpExpenditure(consumepoint);
			consumePointsDetail.setMcpBalance(balance);
			consumePointsDetail.setMcpOperatorType(operatetype);
			consumePointsDetail.setMcpRemark(SerialStringUtil.getPointsRemark(operatetype,phone));
			consumePointsDetail.setMcpCreatedBy(memid);
			consumePointsDetail.setMcpUpdatedBy(memid);
			consumePointsDetail.setMcpCreatedDate(nowDate);
			consumePointsDetail.setMcpUpdatedDate(nowDate);
			i=baseDao.insert(consumePointsDetail,"MSConsumePointsDetailMapper.saveConsumePointsDetails");
		} catch (Exception e) {
			throw e;
		}
		return i;
	}


}
