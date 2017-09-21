package com.meiduimall.service.member.util;

import com.meiduimall.service.member.constant.SysEncrypParamsConst;

/**
 * 字符串序列转化为指定值
 * @author chencong
 *
 */
public class SerialStringUtil {
	
	/**
	 * 数据来源转换成字典值
	 * @param orderSource
	 * @return
	 */
	public static String getDictOrderSource(String orderSource){
		String dictOrderSource = "";
		String upperOrderSource = orderSource.toUpperCase();
		switch (upperOrderSource) {
		case "1gw":
			dictOrderSource = SysEncrypParamsConst.ORDER_SOURCE_1GW;
			break;
		case "o2o":
			dictOrderSource = SysEncrypParamsConst.ORDER_SOURCE_O2O;
			break;
		case "app":
			dictOrderSource = SysEncrypParamsConst.ORDER_SOURCE_APP;
			break;
		case "md":
			dictOrderSource = SysEncrypParamsConst.ORDER_SOURCE_MD;
			break;
		case "md1gw":
			dictOrderSource = SysEncrypParamsConst.ORDER_SOURCE_MD1GW;
			 break;
		default:
			dictOrderSource = SysEncrypParamsConst.ORDER_SOURCE_1GW;
		}
		return dictOrderSource;
	}
	

	/**
	 * 交易类型转换成字典值
	 * @param oprType
	 * @return
	 */
	public static String getDictOperatorType(String oprType){
		String dictId = "";
		String code = oprType.toUpperCase();
		switch (code) {
		case "0":
			dictId = SysEncrypParamsConst.POINTS_OPERATOR_TYPE_QT;
			break;
		case "1":
			dictId = SysEncrypParamsConst.POINTS_OPERATOR_TYPE_CZ;
			break;
		case "3":
			dictId = SysEncrypParamsConst.POINTS_OPERATOR_TYPE_CW;
			break;
		case "4":
			dictId = SysEncrypParamsConst.POINTS_OPERATOR_TYPE_TK;
			break;
		case "5":
			dictId = SysEncrypParamsConst.POINTS_OPERATOR_TYPE_QX;
			break;
		case "6":
			dictId = SysEncrypParamsConst.POINTS_OPERATOR_TYPE_FJXF;
			 break;
		case "7":
			dictId = SysEncrypParamsConst.POINTS_OPERATOR_TYPE_QMTG;
			 break;
		case "8":
			dictId = SysEncrypParamsConst.POINTS_OPERATOR_TYPE_XF;
			 break;
		case "9":
			dictId = SysEncrypParamsConst.POINTS_OPERATOR_TYPE_ZCZS;
			 break;
		case "10":
			dictId = SysEncrypParamsConst.POINTS_OPERATOR_TYPE_YQZCZS;
			 break;
		case "11":
			dictId = SysEncrypParamsConst.POINTS_OPERATOR_TYPE_XJCZ;
			 break;
		case "12":
			dictId = SysEncrypParamsConst.POINTS_OPERATOR_TYPE_JFZR;
			 break;
		case "13":
			dictId = SysEncrypParamsConst.POINTS_OPERATOR_TYPE_JFZC;
			 break;
		default:
			dictId = SysEncrypParamsConst.POINTS_OPERATOR_TYPE_QT;
		}
		return dictId;
	}
	
	/**
	 * 多个交易类型转换成字典值
	 * @param oprTypes
	 * @param splitStr
	 * @return
	 */
	public static String getDictManyOperatorType(String oprTypes, String splitStr){
		String dictIds = "";
		if(StringUtil.isEmptyByString(oprTypes)){
			return dictIds;
		}else{
			String[] oprStrs = oprTypes.split(splitStr);
			int loopCnt = 0;
			for (String type : oprStrs) {
				loopCnt = loopCnt + 1;
				if(loopCnt == oprStrs.length){
					dictIds = dictIds + SerialStringUtil.getDictOperatorType(type);
				}else{
					dictIds = dictIds + SerialStringUtil.getDictOperatorType(type) + ",";
				}
			}
		}
		return dictIds;
	}
	
	/**
	 * 交易类型转换成固定格式备注
	 * @param oprDictId
	 * @param userid
	 * @return
	 */
	public static String getPointsRemark(String oprDictId,String userid){
		String returnStr = "";
		String code = oprDictId.toUpperCase();
		String appendStr = StringUtil.isEmptyByString(userid) ? "" : ("-"+userid);
		
		switch (code) {
		case SysEncrypParamsConst.POINTS_OPERATOR_TYPE_QT:
			returnStr = "外部[其他交易" + appendStr + "]";
			break;
		case SysEncrypParamsConst.POINTS_OPERATOR_TYPE_CZ:
			returnStr = "充值[积分充值" + appendStr + "]";
			break;
		case SysEncrypParamsConst.POINTS_OPERATOR_TYPE_CW:
			returnStr = "系统[后台调整" + appendStr + "]";
			break;
		case SysEncrypParamsConst.POINTS_OPERATOR_TYPE_TK:
			returnStr = "退款[售后退款" + appendStr + "]";
			break;
		case SysEncrypParamsConst.POINTS_OPERATOR_TYPE_QX:
			returnStr = "退款[取消订单" + appendStr + "]";
			break;
		case SysEncrypParamsConst.POINTS_OPERATOR_TYPE_FJXF:
			returnStr = "赠送[附近消费" + appendStr + "]";
			 break;
		case SysEncrypParamsConst.POINTS_OPERATOR_TYPE_QMTG:
			returnStr = "提成[全民推广" + appendStr + "]";
			 break;
		case SysEncrypParamsConst.POINTS_OPERATOR_TYPE_XF:
			returnStr = "消费[积分支付" + appendStr + "]";
			 break;
		case SysEncrypParamsConst.POINTS_OPERATOR_TYPE_ZCZS:
			returnStr = "赠送[新注册送积分" + appendStr + "]";
			 break;
		case SysEncrypParamsConst.POINTS_OPERATOR_TYPE_YQZCZS:
			returnStr = "赠送[推荐注册送积分" + appendStr + "]";
			 break;
		case SysEncrypParamsConst.POINTS_OPERATOR_TYPE_XJCZ:
			returnStr = "充值[现金充值" + appendStr + "]";
			 break;
		case SysEncrypParamsConst.POINTS_OPERATOR_TYPE_JFZC:
			returnStr = "转帐[转出到" + userid + "]";
			if(StringUtil.isEmptyByString(userid)){
				returnStr = "转帐[积分转出]";
			}
			 break;
		case SysEncrypParamsConst.POINTS_OPERATOR_TYPE_JFZR:
			returnStr = "转帐[从" + userid + "转入]";
			if(StringUtil.isEmptyByString(userid)){
				returnStr = "转帐[积分转入]";
			}
			 break;
		default:
			returnStr = "外部[其他交易" + appendStr + "]";
		}
		return returnStr;
	}

}
