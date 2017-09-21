package com.meiduimall.service.financial.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meiduimall.core.ResBodyData;
import com.meiduimall.exception.ApiException;
import com.meiduimall.service.financial.constant.ServiceFinancialApiCode;
import com.meiduimall.service.financial.entity.DownloadStatistics;
import com.meiduimall.service.financial.service.DownloadStatisticsService;
import com.meiduimall.service.financial.util.HttpHeaderTools;

@RestController
@RequestMapping("/financial/financial-system-service/v1/statistics")
public class DownloadStatisticsController {

	private static Logger logger = LoggerFactory.getLogger(DownloadStatisticsController.class);

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private DownloadStatisticsService downloadStatisticsService;

	/**
	 * 插入下载渠道信息
	 * 
	 * @param portal
	 *            渠道编号，必须要传
	 * @return 操作结果
	 */
	@RequestMapping(value = "/insert")
	public ResBodyData insertPortal(String portal) {

		if (StringUtils.isBlank(portal)) {
			logger.error("渠道编号错误：" + portal);
			throw new ApiException(ServiceFinancialApiCode.REQUEST_PARAMS_ERROR,
					ServiceFinancialApiCode.getZhMsg(ServiceFinancialApiCode.REQUEST_PARAMS_ERROR));
		}

		int portalInt = 0;
		try {
			portalInt = Integer.parseInt(portal.trim());
		} catch (NumberFormatException e) {
			logger.error("渠道编号错误：" + e);
			throw new ApiException(ServiceFinancialApiCode.REQUEST_PARAMS_ERROR,
					ServiceFinancialApiCode.getZhMsg(ServiceFinancialApiCode.REQUEST_PARAMS_ERROR));
		}

		DownloadStatistics bean = new DownloadStatistics();
		bean.setPortal(portalInt);

		String ip = HttpHeaderTools.getIpAddr(request);
		bean.setIp(ip);

		String userAgent = request.getHeader("User-Agent");
		bean.setUserAgent(userAgent);

		bean.setRequestTime(new Date(System.currentTimeMillis()));
		return downloadStatisticsService.insert(bean);
	}

	/**
	 * 统计下载渠道信息
	 * 
	 * @param beginDate
	 *            开始时间，传递之前需要进行URL编码，格式'2017-03-08 09:30:25' 或者 '2017-03-08'
	 * @param endDate
	 *            结束时间，传递之前需要进行URL编码，格式'2017-03-08 09:30:25' 或者 '2017-03-08'
	 * @return 统计列表
	 */
	@RequestMapping(value = "/query")
	public ResBodyData queryByDate(String beginDate, String endDate) {

		String beginDateFormat = "";
		String endDateFormat = "";
		try {
			// 特殊处理--日期时间包含空格和冒号，需要进行URL编码和URL解码
			if (!StringUtils.isEmpty(beginDate)) {
				beginDateFormat = URLDecoder.decode(beginDate, "utf-8");
			} else {
				beginDateFormat = beginDate;
			}
			if (!StringUtils.isEmpty(endDate)) {
				endDateFormat = URLDecoder.decode(endDate, "utf-8");
			} else {
				endDateFormat = endDate;
			}
		} catch (UnsupportedEncodingException e) {
			logger.error("对日期进行Decoder错误：" + e);
			throw new ApiException(ServiceFinancialApiCode.REQUEST_PARAMS_ERROR,
					ServiceFinancialApiCode.getZhMsg(ServiceFinancialApiCode.REQUEST_PARAMS_ERROR));
		}
		return downloadStatisticsService.queryByDate(beginDateFormat, endDateFormat);
	}
}
