package com.meiduimall.core;

public class GateWayRequest {
	
	private String clientID;
	
	private String signKey;
	
	public GateWayRequest(String clientID, String signKey) {
		this.clientID = clientID;
		this.signKey = signKey;
	}
	
	public String getClientID() {
		return clientID;
	}
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	public String getSignKey() {
		return signKey;
	}
	public void setSignKey(String signKey) {
		this.signKey = signKey;
	}
	
	
}
