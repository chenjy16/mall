package com.meiduimall.service.member.model;

import java.io.Serializable;

/**
 * 接口统一返回数据(适配专用)
 * 用完可删除
 * @author chencong
 *
 */
public class ResBodyDataShiPei implements Serializable {

	private static final long serialVersionUID = 1L;

  private String status_code;
	
	private String result_msg;
	
	private Object result;
	
	public ResBodyDataShiPei(){}
	
	public ResBodyDataShiPei(String status_code, String result_msg) {
		this.status_code = status_code;
		this.result_msg = result_msg;
	}
	public ResBodyDataShiPei(String status_code, String result_msg, Object result) {
		this.status_code = status_code;
		this.result_msg = result_msg;
		this.result = result;
	}
	

	   public String getStatus_code() {
			return status_code;
		}

		public void setStatus_code(String status_code) {
			this.status_code = status_code;
		}

		public String getResult_msg() {
			return result_msg;
		}

		public void setResult_msg(String result_msg) {
			this.result_msg = result_msg;
		}

		public Object getResult() {
			return result;
		}

		public void setResult(Object result) {
			this.result = result;
		}

		@Override
		public String toString() {
			return "ResBodyDataShiPei [status_code=" + status_code + ", result_msg=" + result_msg + ", result=" + result
					+ "]";
		}
		
}
