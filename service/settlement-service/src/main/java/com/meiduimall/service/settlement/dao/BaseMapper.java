package com.meiduimall.service.settlement.dao;

import java.util.List;

/**
 * 数据访问基础接口
 * @author guidl
 *
 */
public interface BaseMapper {
	
	/**
	 * 根据参数查询获取对象
	 * @param params 参数
	 * @param sqlTag 对应Mapper.xml文件中的sql id
	 * @return T
	 */
	public <T, P> T selectOne(P params, String sqlTag);
	
	/**
	 * 根据参数查询集合列表
	 * @param params 参数
	 * @param sqlTag 对应Mapper.xml文件中的sql id
	 * @return T
	 */
	public <T, P> List<T> selectList(P params, String sqlTag);

	/**
	 * 新增
	 * @param t 参数
	 * @param sqlTag 对应Mapper.xml文件中的sql id
	 * @return Integer
	 */
	public <T> Integer insert(T t, String sqlTag);

	/**
	 * 批量新增
	 * @param ts 参数
	 * @param sqlTag 对应Mapper.xml文件中的sql id
	 * @return Integer
	 */ 
	public <T> Integer insertBatch(List<T> ts, String sqlTag);

	/**
	 * 更新
	 * @param params 参数
	 * @param sqlTag 对应Mapper.xml文件中的sql id
	 * @return Integer
	 */
	public <P> Integer update(P params, String sqlTag);

	/**
	 * 删除
	 * @param params 参数
	 * @param sqlTag 对应Mapper.xml文件中的sql id
	 * @return Integer
	 */
	public <P> Integer delete(P params, String sqlTag);

	/**
	 * 批量删除
	 * @param params 参数
	 * @param sqlTag 对应Mapper.xml文件中的sql id
	 * @return Integer
	 */
	public <P> Integer deleteBatch(List<P> params, String sqlTag);
}