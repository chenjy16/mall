package com.meiduimall.service.sms.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AliyunRequestModel {

	public class Result {

		@JsonProperty("err_code")
		private String errCode;

		private String model;
		private String success;

		public String getErrCode() {
			return errCode;
		}

		public void setErrCode(String errCode) {
			this.errCode = errCode;
		}

		public String getModel() {
			return model;
		}

		public void setModel(String model) {
			this.model = model;
		}

		public String getSuccess() {
			return success;
		}

		public void setSuccess(String success) {
			this.success = success;
		}

	}

	private Result result;

	@JsonProperty("request_id")
	private String requestId;

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
}
