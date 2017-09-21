package com.meiduimall.service.settlement.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableMap;
import com.meiduimall.exception.DaoException;
import com.meiduimall.exception.ServiceException;
import com.meiduimall.service.settlement.common.CodeRuleUtil;
import com.meiduimall.service.settlement.common.SettlementUtil;
import com.meiduimall.service.settlement.dao.BaseMapper;
import com.meiduimall.service.settlement.model.EcmMzfAccount;
import com.meiduimall.service.settlement.model.EcmMzfBillWater;
import com.meiduimall.service.settlement.model.EcmMzfOrderStatus;
import com.meiduimall.service.settlement.model.EcmMzfWater;
import com.meiduimall.service.settlement.service.AgentService;
import com.meiduimall.service.settlement.service.BeanSelfAware;
import com.meiduimall.service.settlement.service.BillService;
import com.meiduimall.service.settlement.util.DateUtil;
import com.meiduimall.service.settlement.vo.BilledWaterVO2Merge;
import com.meiduimall.service.settlement.vo.OrderToBilledVO;

/**
 * 生成账单，生成账单流水，更新账户
 * @author chencong
 *
 */
@Service
public class BillServiceImpl implements BillService,BeanSelfAware {
	
	protected final Logger log = LoggerFactory.getLogger(BillServiceImpl.class);
	
	@Autowired
	private BaseMapper baseMapper;
	
	@Autowired
	private AgentService agentService;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * spring声明式事务 同一类内方法调用事务失效
	 * //http://blog.csdn.net/jiesa/article/details/53438342
	 */
	@Autowired
	private BillService proxySelf;

	@Override
	public void setSelf(Object proxyBean) {
		this.proxySelf=(BillService) proxyBean;
 
	}
	

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public Collection<String> createBills()  {
		
		Collection<String> orderSns=new ArrayList<>();
		
		Long verifyTime=DateUtil.getLastDayEndBySecond();
		//查询账单
		List<EcmMzfBillWater> billList = baseMapper.selectList(verifyTime, "EcmBillMapper.queryReviewedShareOrder");
		List<OrderToBilledVO> orderToBilledList = baseMapper.selectList(verifyTime, "EcmBillMapper.queryOrderToBilled");
		
		Date billCreatedtime = DateUtil.getParseDate(DateUtil.getCurrentDay()); // 账单创建日期
		Date billtime = DateUtil.getParseDate(DateUtil.getUpDAY()); // 账单日期
		log.info("账单生成时间:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" 账单日期："+billtime+" 需要插入到账单流水的条数："+billList.size());
		if(!billList.isEmpty()){
			
			Timestamp waterOpTime=new Timestamp(System.currentTimeMillis());
			for (int j=0;j<billList.size();j++) {
				EcmMzfBillWater bill=billList.get(j);
				
				try {
					this.proxySelf.handleBill(bill,billCreatedtime,billtime,waterOpTime);
					this.proxySelf.createBillAndOrderMapping(bill,orderToBilledList);
				} catch (Exception e) {
					log.error("编号为:{},账单处理异常:{}",bill.getCode(),e.getMessage());
					throw e;
				}
			}
			
			Set<String> orderNos=new HashSet<>();
			for(OrderToBilledVO vo:orderToBilledList){
				orderNos.add(vo.getOrderSn());  //get the unique orderSn collection.
			}
			this.proxySelf.updateOrderBillStatus(orderNos);
			orderSns.addAll(orderNos);
		}
		
		return orderSns;
	}
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void handleBill(EcmMzfBillWater bill,Date billCreatedtime,Date billtime,Timestamp opTime){
		bill.setBillAddTime(billCreatedtime); //设置账单创建日期
		bill.setBillTime(billtime);//设置账单日期
		double amount=bill.getAmount(); //账单金额
		int type=bill.getType();//角色类型
		String code=bill.getCode();//角色编号
		//设置账单编号
		String billid=CodeRuleUtil.getBillid(bill.getType(),bill.getCode());
		bill.setBillId(billid);
		//开始生成账单流水数据
		if(!Strings.isNullOrEmpty(code)){
			int i=baseMapper.insert(bill,"EcmBillMapper.createBillWater");
			log.info("生成编号为{}的账单数据:{}条   金额:{} 角色类型:", code, i, amount, type);
			
			//BUG #3029::金额为0的账单不需要生成流水
			if(!SettlementUtil.isZero(amount)){
				
				//获取账户余额(2017-04-01)
				EcmMzfAccount ecmMzfAccount = agentService.findAccountByCode(code);
				
				//账单数据插入到流水汇总表
				EcmMzfWater water = new EcmMzfWater();
				water.setWaterId(CodeRuleUtil.getBillFlowCode(type,code));
				water.setRemark(sdf.format(billtime)+"账单");
				water.setCode(code);
				water.setMoney(BigDecimal.valueOf(amount));
				water.setWaterType("2");
				water.setExtId(billid);
				water.setOpTime(opTime);
				water.setBalance(ecmMzfAccount.getBalance());//变更前可提现金额(2017-04-01)
				int i2=agentService.insertWater(water);
				log.info("生成编号为{}的流水汇总数据:{}条  金额:{} 角色类型:{}", code, i2, amount, type);	
				
				//更新账户
				String newtype=String.valueOf(CodeRuleUtil.getAccountRoleType(type));
				EcmMzfAccount ccount = new EcmMzfAccount();
				ccount.setAccountRoleType(newtype);
				ccount.setBalance(BigDecimal.valueOf(amount));//double改为BigDecimal
				ccount.setCode(code);
				int d=agentService.updateAccount(ccount);
				log.info("更新编号{}的账户:{}条 金额:{} 角色类型:{}", code, d, amount, newtype);
			}
		}
	}
	
	

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=ServiceException.class)
	public void createBillAndOrderMapping(EcmMzfBillWater bill, List<OrderToBilledVO> orderToBilledList)  {
		if(bill!=null && !Strings.isNullOrEmpty(bill.getCode())){
			final String code=bill.getCode();

			Collection<OrderToBilledVO> orderToBilledVos = Collections2.filter(orderToBilledList, vo -> code.equalsIgnoreCase(vo.getCode()));
			
			if(orderToBilledVos!=null && !orderToBilledVos.isEmpty()){
				for(OrderToBilledVO ob:orderToBilledVos){
					createBillAndOrder(bill, ob);
				}
			}
		}
	}


	private void createBillAndOrder(EcmMzfBillWater bill, OrderToBilledVO ob) {
		try {
			ob.setBillId(bill.getBillId());
			baseMapper.insert(ob, "EcmBillMapper.createBillAndOrderMapping");
		} catch (DaoException e) {
			log.error("EcmBillMapper.createBillAndOrderMapping got error for orderSn:{},type:{},billId:{},{}",ob.getOrderSn(), ob.getType(), ob.getBillId(), e);
			throw e;
		}
	}
	

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateOrderBillStatus(Collection<String> orderSnList)  {
		if(orderSnList!=null && !orderSnList.isEmpty()){
			for(String orderSn:orderSnList){	
				try {
					EcmMzfOrderStatus os = new EcmMzfOrderStatus();
					os.setOrderSn(orderSn);
					os.setBillStatus(3);
					os.setStatusDesc("已结算");
					baseMapper.update(os, "EcmMzfOrderStatusMapper.updateBillStatus");
					log.info("EcmMzfOrderStatusMapper.updateBillStatus success: orderSn:" + orderSn);
				} catch (Exception e) {
					log.error("EcmMzfOrderStatusMapper.updateBillStatus got error for orderSn:" + orderSn, e);
					throw e;
				}
			}
		}
		
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@Override
	public void mergeBilledWaters(List<BilledWaterVO2Merge> mergeWaterVOList) {
		
		if(mergeWaterVOList!=null && !mergeWaterVOList.isEmpty()){
			final List<Integer> ids2Del=new ArrayList<>();
			final List<Integer> ids2Keep=new ArrayList<>();
			final Map<Integer,Integer> idRelations=new HashMap<>();
			
			for(BilledWaterVO2Merge vo:mergeWaterVOList){
				Integer id2Keep=vo.getIdKeep();
				Integer id2Del=vo.getIdDel();
				ids2Del.add(id2Del);
				ids2Keep.add(id2Keep);
				idRelations.put(id2Keep, id2Del);
			}
			
			List<EcmMzfWater> ecmMzfWaterList2Del = baseMapper.selectList(ImmutableMap.of("ids",ids2Del), "EcmMzfWaterMapper.getWatersByIds");
			
			List<EcmMzfWater> ecmMzfWaterList2Keep = baseMapper.selectList(ImmutableMap.of("ids",ids2Keep), "EcmMzfWaterMapper.getWatersByIds");
			
			Map<Integer,EcmMzfWater> ecmMzfWaterMap2Del=SettlementUtil.convert2Map(ecmMzfWaterList2Del, "id");
			
			//合并同一用户重复流水money
			for(EcmMzfWater water:ecmMzfWaterList2Keep){
				Integer id2keep=water.getId();
				Integer id2Del=idRelations.get(id2keep);
				EcmMzfWater water2Del=ecmMzfWaterMap2Del.get(id2Del);
				water.setMoney(water.getMoney().add(water2Del.getMoney()));
			}
			//更新money
			for(EcmMzfWater water:ecmMzfWaterList2Keep){
				baseMapper.update(water, "EcmMzfWaterMapper.updateMoneybyId");
			}
			//删除流水
			baseMapper.delete(ImmutableMap.of("ids",ids2Del), "EcmMzfWaterMapper.delWatersByIds");
		}
	}

}