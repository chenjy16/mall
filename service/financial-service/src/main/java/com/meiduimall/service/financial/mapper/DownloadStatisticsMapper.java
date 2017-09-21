package com.meiduimall.service.financial.mapper;

import com.meiduimall.service.financial.entity.DownloadStatistics;

public interface DownloadStatisticsMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(DownloadStatistics record);

    int insertSelective(DownloadStatistics record);

    DownloadStatistics selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DownloadStatistics record);

    int updateByPrimaryKeyWithBLOBs(DownloadStatistics record);

    int updateByPrimaryKey(DownloadStatistics record);
}