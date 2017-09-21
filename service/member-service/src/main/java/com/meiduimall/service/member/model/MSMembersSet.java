package com.meiduimall.service.member.model;

import java.io.Serializable;
import java.util.Date;

import com.meiduimall.exception.SystemException;
import com.meiduimall.service.member.util.DESC;
import com.meiduimall.service.member.util.StringUtil;

/**
 * 
 * 会员基础表(加密)
 * 
 **/
public class MSMembersSet implements Serializable {

	private static final long serialVersionUID = 4550764542554779921L;

	/** 会员系统ID **/
	private String memId;
	
	/**用户姓名*/
	private String memName;

	/** 会员登录名 **/
	private String memLoginName;

	/** 会员上一次绑定手机号 **/
	private String memOldPhone;

	/** 会员最新手机号 **/
	private String memPhone;

	/** 会员昵称 **/
	private String memNickName;

	/** 会员登录密码 **/
	private String memLoginPwd;

	/** 会员支付密码 **/
	private String memPayPwd;
	
	/**是否分配默认登录名*/
	private String memLoginNameIsdefaultIschanged;

	/** 会员推荐人ID **/
	private String memParentId;

	/** 会员状态 **/
	private String dictMemStatus;

	/** 会员是否所有绑定 **/
	private  Boolean memIsAllActivated;

	/** 会员创建时间 **/
	private java.util.Date memCreatedDate;
	
	/** 会员本次登录时间 **/
	private java.util.Date memLoginTime;

	/** 会员更新时间 **/
	private java.util.Date memUpdatedDate;

	/** 会员性别 **/
	private String memSex;

	/** 会员生日 **/
	private java.util.Date memBirthday;

	/** 会员自定义头像,主要给APP或PC应用 **/
	private String memPic;

	/** 会员当前详细住址 **/
	private String memCurrentAddress;

	/** 注册年份 **/
	private Integer memRegYear;

	/** 会员注册月份 **/
	private Integer memRegMonth;

	/** 会员注册天 **/
	private Integer memRegDay;

	/** 会员更新人ID **/
	private String memUpdatedBy;

	/** 会员创建人ID **/
	private String memCreatedBy;

	/** 会员创建种类。1为自己创建。2位平台创建。 **/
	private Integer memCreatedCategory;

	/** 父节点ID串1 **/
	private String memParentValue1;

	/** 父节点ID串2 **/
	private String memParentValue2;

	/** 父节点ID串3 **/
	private String memParentValue3;

	/** License Key **/
	private String licenseKey;

	/** 基本账号总额 **/
	private String memBasicAccountTotalQuantity;

	/** 基本账号状态ID **/
	private String memBasicAccountStatus;

	/** 积分状态 **/
	private String memIntegralStatus;

	/** 钱包地址 **/
	private String memWallentUrl;

	/** 上次登录时间 **/
	private java.util.Date pfLastLoginTime;
	
	/**
	 * 当前级别
	 */
	private String memPresentLevel;
	
	
	/**
	 * 用户类型：1代表会员，2，代表经销商，3，代表公司运营
	 */
	private String memType;
	
	
	/**注册来源0表示PC端注册1表示o2o注册2表示会员结算系统数据迁移注册3表示壹购物注册4表示壹购物商城迁移*/
	private String memSignSource;
	
	/**
	 * 冻结积分
	 */
	private String memIntegralFrozen;

	/**
	 * 是否开设店铺1为允许.0为为禁止
	 */
	private String memIsAllowShop;
	
	/** 会员账户被禁用/解禁的时间 **/
	private Date memBanDate;
	
	/** 修改手机号时间 **/
	private Date changePhoneDate;
	
	/**锁定次数*/
	private String memLockCount;
		

	public Date getMemLoginTime() {
		return memLoginTime;
	}

	public Date changePhoneDate() {
		return changePhoneDate;
	}
	
	public java.util.Date getChangePhoneDate() {
		return changePhoneDate;
	}

	public void setChangePhoneDate(java.util.Date changePhoneDate) {
		this.changePhoneDate = changePhoneDate;
	}

	public void setMemLoginTime(java.util.Date memLoginTime) {
		this.memLoginTime = memLoginTime;
	}
	
	public java.util.Date getMemBanDate() {
		return memBanDate;
	}

	public java.util.Date memBandate() {
		return memBanDate;
	}

	public void setMemBanDate(java.util.Date memBanDate) {
		this.memBanDate = memBanDate;
	}

	public String getMemIntegralFrozen() {
		return memIntegralFrozen;
	}

	public void setMemIntegralFrozen(String memIntegralFrozen) throws SystemException{
		this.memIntegralFrozen = DESC.encryption(memIntegralFrozen,this.memId);
	}

	public String getMemIsAllowShop() {
		return memIsAllowShop;
	}

	public void setMemIsAllowShop(String memIsAllowShop) {
		this.memIsAllowShop = memIsAllowShop;
	}

	public String getMemType() {
		return memType;
	}

	public void setMemType(String memType) {
		this.memType = memType;
	}

	public String getMemBasicAccountTotalQuantity() {
		return memBasicAccountTotalQuantity;
	}

	public void setMemBasicAccountTotalQuantity(String memBasicAccountTotalQuantity) throws SystemException{
		this.memBasicAccountTotalQuantity = DESC.encryption(memBasicAccountTotalQuantity==null?"0":memBasicAccountTotalQuantity, this.memId);
	}

	public String getMemPresentLevel() {
		return memPresentLevel;
	}

	public void setMemPresentLevel(String memPresentLevel) {
		this.memPresentLevel = memPresentLevel;
	}

	public String getMemSignSource() {
		return memSignSource;
	}

	public void setMemSignSource(String memSignSource) {
		this.memSignSource = memSignSource;
	}

	public String getMemName() throws SystemException {
		return DESC.deyption(this.memName);
	}

	public void setMemName(String memName) throws SystemException  {
		this.memName = DESC.encryption(memName);
	}
	
	public Date getPfLastLoginTime() {
		return pfLastLoginTime;
	}

	public void setPfLastLoginTime(java.util.Date pfLastLoginTime) {
		this.pfLastLoginTime = pfLastLoginTime;
	}

	public String getMemWallentUrl() {
		return memWallentUrl;
	}

	public void setMemWallentUrl(String memWallentUrl) {
		this.memWallentUrl = memWallentUrl;
	}

	public String getLicenseKey() {
		return licenseKey;
	}

	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}

	public String getMemBasicAccountTotal() {
		return this.memBasicAccountTotalQuantity;
	}

	public void setMemBasicAccountTotal(String memBasicAccountTotalQuantity)throws SystemException {
		this.memBasicAccountTotalQuantity = DESC.encryption(memBasicAccountTotalQuantity, this.memId);
	}

	public String getMemBasicAccountStatus() {
		return memBasicAccountStatus;
	}

	public void setMemBasicAccountStatus(String memBasicAccountStatus) {
		this.memBasicAccountStatus = memBasicAccountStatus;
	}

	public String getMemIntegralStatus() {
		return memIntegralStatus;
	}

	public void setMemIntegralStatus(String memIntegralStatus)throws SystemException {
		this.memIntegralStatus = DESC.encryption(memIntegralStatus,this.memId);
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemId() {
		return this.memId;
	}

	public void setMemLoginName(String memLoginName)  throws SystemException {
		this.memLoginName = DESC.encryption(memLoginName);
	}

	public String getMemLoginName() {
		return this.memLoginName;
	}

	public void setMemOldPhone(String memOldPhone) throws SystemException  {
		this.memOldPhone = DESC.encryption(memOldPhone);
	}

	public String getMemOldPhone() {
		return this.memOldPhone;
	}

	public void setMemPhone(String memPhone) throws SystemException  {
		this.memPhone = DESC.encryption(memPhone);
	}

	public String getMemPhone() {
		return this.memPhone;
	}

	public void setMemNickName(String memNickName) throws SystemException  {
		this.memNickName = DESC.encryption(memNickName);
	}

	public String getMemNickName() {
		return this.memNickName;
	}

	public void setMemLoginPwd(String memLoginPwd) {
		this.memLoginPwd = memLoginPwd;
	}

	public String getMemLoginPwd() {
		return this.memLoginPwd;
	}

	public void setMemPayPwd(String memPayPwd) {
		this.memPayPwd = memPayPwd;
	}

	public String getMemPayPwd() {
		return this.memPayPwd;
	}
	
	public String getMemLoginNameIsdefaultIschanged() {
		return memLoginNameIsdefaultIschanged;
	}
	
	public void setMemLoginNameIsdefaultIschanged(String memLoginNameIsdefaultIschanged) throws SystemException {
		this.memLoginNameIsdefaultIschanged = DESC.encryption(memLoginNameIsdefaultIschanged,memId);
	}

	public void setMemParentId(String memParentId) {
		this.memParentId = memParentId;
	}

	public String getMemParentId() {
		return this.memParentId;
	}

	public void setDictMemStatus(String dictMemStatus) {
		this.dictMemStatus = dictMemStatus;
	}

	public String getDictMemStatus() {
		return this.dictMemStatus;
	}

	public void setMemIsAllActivated(Boolean memIsAllActivated) {
		this.memIsAllActivated = memIsAllActivated;
	}

	public Boolean getMemIsAllActivated() {
		return this.memIsAllActivated;
	}

	public void setMemCreatedDate(java.util.Date memCreatedDate) {
		this.memCreatedDate = memCreatedDate;
	}

	public java.util.Date getMemCreatedDate() {
		return this.memCreatedDate;
	}

	public void setMemUpdatedDate(java.util.Date memUpdatedDate) {
		this.memUpdatedDate = memUpdatedDate;
	}

	public java.util.Date getMemUpdatedDate() {
		return this.memUpdatedDate;
	}

	public void setMemSex(String memSex) {
		this.memSex = memSex;
	}

	public String getMemSex() {
		return this.memSex;
	}

	public void setMemBirthday(java.util.Date memBirthday) {
		this.memBirthday = memBirthday;
	}

	public java.util.Date getMemBirthday() {
		return this.memBirthday;
	}

	public void setMemPic(String memPic) throws SystemException{
		this.memPic = DESC.encryption(memPic,this.memId);
	}

	public String getMemPic() {
		return this.memPic;
	}

	public void setMemCurrentAddress(String memCurrentAddress) {
		this.memCurrentAddress = memCurrentAddress;
	}

	public String getMemCurrentAddress() {
		return this.memCurrentAddress;
	}

	public void setMemRegYear(Integer memRegYear) {
		this.memRegYear = memRegYear;
	}

	public Integer getMemRegYear() {
		return this.memRegYear;
	}

	public void setMemRegMonth(Integer memRegMonth) {
		this.memRegMonth = memRegMonth;
	}

	public Integer getMemRegMonth() {
		return this.memRegMonth;
	}

	public void setMemRegDay(Integer memRegDay) {
		this.memRegDay = memRegDay;
	}

	public Integer getMemRegDay() {
		return this.memRegDay;
	}

	public void setMemUpdatedBy(String memUpdatedBy) {
		this.memUpdatedBy = memUpdatedBy;
	}

	public String getMemUpdatedBy() {
		return this.memUpdatedBy;
	}

	public void setMemCreatedBy(String memCreatedBy) {
		this.memCreatedBy = memCreatedBy;
	}

	public String getMemCreatedBy() {
		return this.memCreatedBy;
	}

	public void setMemCreatedCategory(Integer memCreatedCategory) {
		this.memCreatedCategory = memCreatedCategory;
	}

	public Integer getMemCreatedCategory() {
		return this.memCreatedCategory;
	}

	public void setMemParentValue1(String memParentValue1) {
		this.memParentValue1 = memParentValue1;
	}

	public String getMemParentValue1() {
		return this.memParentValue1;
	}

	public void setMemParentValue2(String memParentValue2) {
		this.memParentValue2 = memParentValue2;
	}

	public String getMemParentValue2() {
		return this.memParentValue2;
	}

	public void setMemParentValue3(String memParentValue3) {
		this.memParentValue3 = memParentValue3;
	}

	public String getMemParentValue3() {
		return this.memParentValue3;
	}

	public void setMemLicenseKey(String licensekey) {
		this.licenseKey = licensekey;
	}

	public String getMemLicenseKey() {
		return this.licenseKey;
	}

	public String getMemLockCount() {
		return StringUtil.isEmptyByString(memLockCount)?"0":memLockCount;
	}

	public void setMemLockCount(String memLockCount) throws SystemException{
		this.memLockCount =  DESC.encryption((memLockCount==null?"0":memLockCount),memId);
	}

}