package com.meiduimall.service.catalog.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.phprpc.util.AssocArray;
import org.phprpc.util.PHPSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParseSkuSpecDescUtil {

	private static Logger logger = LoggerFactory.getLogger(ParseSkuSpecDescUtil.class);

	private ParseSkuSpecDescUtil() {
	}

	/**
	 * 解析表sysitem_sku，字段spec_desc(商品SKU序列化数据),格式如下：
	 * 
	 * {spec_value_id={43=437, 44=440, 45=447, 46=450},
	 * spec_private_value_id={43=, 44=, 45=, 46=}, spec_value={43=茅台葡萄酒, 44=12度,
	 * 45=干红, 46=贵州}}
	 * 
	 * @param content
	 *            数据格式如下： a:3:{s:13:"spec_value_id";a:4:
	 *            {i:46;s:3:"450";i:43;s:3:"437";i:44;s:3:"440";i:45;s:3:"447";}
	 *            s:10:"spec_value";a:4:{i:46;s:6:"贵州";i:43;s:15:"茅台葡萄酒";i:44;
	 *            s:5:"12度";i:45;s:6:"干红";}s:21:"spec_private_value_id";a:4:{i:46;s:
	 *            0:"";i:43;s:0:"";i:44;s:0:"";i:45;s:0:"";}}
	 * @return SKU信息列表
	 */
	public static List<ParseSkuSpecDescBean> parse(String content) {

		if (content == null || "null".equals(content) || content.trim().length() == 0) {
			return null;
		}
		PHPSerializer p = new PHPSerializer();
		AssocArray array = null;
		try {
			array = (AssocArray) p.unserialize(content.getBytes());
		} catch (Exception e) {
			logger.error("解析商品SKU序列化数据，异常： " + e);
			return null;
		}
		if (array == null || array.isEmpty()) {
			return null;
		}

		@SuppressWarnings("unchecked")
		HashMap<String, AssocArray> map = array.toHashMap();
		if (map == null || map.isEmpty()) {
			return null;
		}

		// 这里创建一个TreeMap集合是为了对数据进行排序。
		Map<Integer, ParseSkuSpecDescBean> resultMap = new TreeMap<Integer, ParseSkuSpecDescBean>();
		for (Map.Entry<String, AssocArray> entry : map.entrySet()) {
			String key = entry.getKey();
			AssocArray value = entry.getValue();
			if (value == null || value.isEmpty()) {
				continue;
			}

			@SuppressWarnings("unchecked")
			HashMap<Integer, Object> map2 = value.toHashMap();
			if (map2 == null || map2.isEmpty()) {
				continue;
			}
			for (Map.Entry<Integer, Object> entry2 : map2.entrySet()) {
				Integer key2 = entry2.getKey();
				Object value2 = entry2.getValue();
				byte[] bs = (byte[]) value2;
				String result = null;
				if (bs != null && bs.length > 0) {
					try {
						result = new String(bs, "utf-8");
					} catch (UnsupportedEncodingException e) {
						logger.error("解析商品SKU序列化数据，异常： " + e);
					}
				} else {
					continue;
				}

				// 数据解析完毕，开始将数据放到resultMap集合中，自动排序
				ParseSkuSpecDescBean bean = resultMap.get(key2);
				if (bean == null) {
					bean = new ParseSkuSpecDescBean();
					bean.setPropId(key2);
				}
				if ("spec_value_id".equals(key)) {
					bean.setPropValueId(Integer.parseInt(result));
				} else if ("spec_value".equals(key)) {
					bean.setSpecValue(result);
				} else {
					continue;
				}
				resultMap.put(key2, bean);
			}
		}

		// 将Map数据转为List数据，并返回
		List<ParseSkuSpecDescBean> list = new ArrayList<ParseSkuSpecDescBean>();
		for (Map.Entry<Integer, ParseSkuSpecDescBean> entry : resultMap.entrySet()) {
			list.add(entry.getValue());
		}
		return list;
	}
}
