package com.meiduimall.service.member.model.response;

import java.io.Serializable;

public class RankConfigDTO implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private String rankName; //级别名称
	private String rankEnName;//级别名称简写
	private double rankRatio;// 级别比率
	private String rankDictId;// 级别字典id
	private String rankIntegral;// 级别积分
	private int rankNo;
	
	
	public int getRankNo() {
		return rankNo;
	}
	public void setRankNo(int rankNo) {
		this.rankNo = rankNo;
	}
	public String getRankName() {
		return rankName;
	}
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}
	public double getRankRatio() {
		return rankRatio;
	}
	public void setRankRatio(double rankRatio) {
		this.rankRatio = rankRatio;
	}
	public String getRankDictId() {
		return rankDictId;
	}
	public void setRankDictId(String rankDictId) {
		this.rankDictId = rankDictId;
	}
	public String getRankIntegral() {
		return rankIntegral;
	}
	public void setRankIntegral(String rankIntegral) {
		this.rankIntegral = rankIntegral;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getRankEnName() {
		return rankEnName;
	}
	public void setRankEnName(String rankEnName) {
		this.rankEnName = rankEnName;
	}
	
}
