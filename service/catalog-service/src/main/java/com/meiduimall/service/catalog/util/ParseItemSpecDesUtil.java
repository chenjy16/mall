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

import com.meiduimall.service.catalog.util.ParseItemSpecDescBean.PropValueBean;

public class ParseItemSpecDesUtil {

	private static Logger logger = LoggerFactory.getLogger(ParseItemSpecDesUtil.class);

	private ParseItemSpecDesUtil() {
	}

	/**
	 * 解析：表sysitem_sku，字段spec_desc序列化数据,格式字符串
	 * 
	 * { 4={ 64={spec_value_id=64, spec_value=1号自然棕色, private_spec_value_id=},
	 * 43={spec_value_id=43, spec_value=2号浅棕色, spec_private_value_id=[],
	 * private_spec_value_id=} }, 25={ 245={spec_value_id=245,
	 * spec_value=CLIOCLIO珂莱欧, private_spec_value_id=} } }
	 * 
	 * @param content
	 *            数据格式:
	 *            a:2:{i:4;a:2:{i:64;a:3:{s:13:"spec_value_id";s:2:"64";s:10:"spec_value";s:16:
	 *            "1号自然棕色";s:21:"private_spec_value_id";s:0:"";}i:43;a:4:{s:21:"private_spec_value_id"
	 *            ;s:0:"";s:10:"spec_value";s:13:"2号浅棕色";s:13:"spec_value_id";s:2:"43";s:21:
	 *            "spec_private_value_id";a:0:{}}}i:25;a:1:{i:245;a:3:{s:13:"spec_value_id";s:3:"245
	 *            ";s:10:"spec_value";s:17:"CLIOCLIO珂莱欧";s:21:"private_spec_value_id";s:0:"";}}}
	 * 
	 * @return 规格列表
	 */
	public static List<ParseItemSpecDescBean> parse(String content) {
		if (content == null || "null".equals(content) || content.trim().length() == 0) {
			return null;
		}

		PHPSerializer p = new PHPSerializer();
		AssocArray array = null;
		try {
			array = (AssocArray) p.unserialize(content.getBytes());
		} catch (Exception e) {
			logger.error("解析：表sysitem_sku，字段spec_desc，异常： " + e);
			return null;
		}
		if (array == null || array.isEmpty()) {
			return null;
		}
		@SuppressWarnings("unchecked")
		HashMap<Integer, AssocArray> map = array.toHashMap();
		if (map == null || map.isEmpty()) {
			return null;
		}

		// 这里创建一个TreeMap集合是为了对ParseItemSpecDescBean进行排序。
		Map<Integer, ParseItemSpecDescBean> resultMap = new TreeMap<Integer, ParseItemSpecDescBean>();
		// 第一次循环，取出规格ID--4,25
		for (Map.Entry<Integer, AssocArray> entry : map.entrySet()) {
			Integer key = entry.getKey(); // 4,25
			AssocArray value = entry.getValue();
			if (value == null || value.isEmpty()) {
				continue;
			}
			@SuppressWarnings("unchecked")
			HashMap<Integer, AssocArray> map2 = value.toHashMap();
			if (map2 == null || map2.isEmpty()) {
				continue;
			}

			// 封装数据封装到ParseItemSpecDescBean
			ParseItemSpecDescBean itemBean = resultMap.get(key);
			if (itemBean == null) {
				itemBean = new ParseItemSpecDescBean();
				itemBean.setPropId(key);
				itemBean.setPropValueBeanList(new ArrayList<PropValueBean>());
			}

			// 这里创建一个TreeMap集合是为了对PropValueBean进行排序。
			Map<Integer, PropValueBean> tempMap = new TreeMap<Integer, PropValueBean>();
			// 第二次循环只是为了取出最内侧AssocArray
			for (Map.Entry<Integer, AssocArray> entry2 : map2.entrySet()) {
				Integer key2 = entry2.getKey(); // 64,43,245
				AssocArray value2 = entry2.getValue();
				if (value2 == null || value2.isEmpty()) {
					continue;
				}
				@SuppressWarnings("unchecked")
				HashMap<String, Object> map3 = value2.toHashMap();
				if (map3 == null || map3.isEmpty()) {
					continue;
				}

				// 第三次循环，取出spec_value_id和spec_value对应的值
				for (Map.Entry<String, Object> entry3 : map3.entrySet()) {
					String key3 = entry3.getKey();// spec_value_id,spec_value
					Object value3 = entry3.getValue();
					byte[] bs = null;
					try {
						bs = (byte[]) value3;
					} catch (Exception e) {
						logger.error("解析：表sysitem_sku，字段spec_desc，异常： " + e);
						continue;
					}
					String result = null;
					if (bs != null && bs.length > 0) {
						try {
							result = new String(bs, "utf-8");
						} catch (UnsupportedEncodingException e) {
							logger.error("解析：表sysitem_sku，字段spec_desc，异常： " + e);
						}
					} else {
						continue;
					}

					// 将数据封装到PropValueBean
					PropValueBean valueBean = tempMap.get(key2);
					if (valueBean == null) {
						valueBean = new ParseItemSpecDescBean().new PropValueBean();
					}
					if ("spec_value_id".equals(key3)) {
						valueBean.setSpecValueId(Integer.parseInt(result));
					} else if ("spec_value".equals(key3)) {
						valueBean.setSpecValue(result);
					} else {
						continue;
					}
					tempMap.put(key2, valueBean);
				}
			}

			for (Map.Entry<Integer, PropValueBean> entry4 : tempMap.entrySet()) {
				itemBean.getPropValueBeanList().add(entry4.getValue());
			}
			resultMap.put(key, itemBean);
		}

		// 将Map数据转为List数据，并返回
		List<ParseItemSpecDescBean> list = new ArrayList<ParseItemSpecDescBean>();
		for (Map.Entry<Integer, ParseItemSpecDescBean> entry : resultMap.entrySet()) {
			list.add(entry.getValue());
		}
		return list;
	}
}
