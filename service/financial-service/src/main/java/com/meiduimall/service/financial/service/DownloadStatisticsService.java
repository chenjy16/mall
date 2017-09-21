package com.meiduimall.service.financial.service;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.service.financial.entity.DownloadStatistics;

/**
 * 渠道下载信息服务类
 * 
 * @author yangchangfu
 *
 */
public interface DownloadStatisticsService {

	/**
	 * 插入数据
	 * 
	 * @param downloadStatistics
	 *            插入渠道对象
	 * @return 操作结果
	 */
	ResBodyData insert(DownloadStatistics downloadStatistics);

	/**
	 * 统计下载渠道
	 * 
	 * @param beginDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return 统计列表
	 */
	ResBodyData queryByDate(String beginDate, String endDate);
}
