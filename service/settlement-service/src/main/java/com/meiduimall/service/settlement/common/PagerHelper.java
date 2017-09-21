package com.meiduimall.service.settlement.common;

/**
 * Copyright (C), 2002-2017, 美兑壹购
 * FileName: PagerHelper.java
 * Author:   吴军
 * Date:     2017年3月15日 下午6:15:47
 * Description: 分页参数辅助类
 */
public class PagerHelper {

	private int pageNumber = 1;
	private int pageSize = 10;

	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	 
}
