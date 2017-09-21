package com.meiduimall.core.util;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class JsonUtilsTest {
	
	
	private String jsonStr="{\"name\":\"张三\",\"age\":22,\"list\":[1,2,3,4],\"map\":{},\"date\":\"2001-07-04T12:08:56.235-07:00\"}";
	private String listStr="[1,2,34,5]";
	private String mapStr="{\"age\":13}";
	
	
	@Test
	public void jsonToBeanTest(){
		Json json=JsonUtils.jsonToBean(jsonStr, Json.class);
		Assert.assertNotNull(json);
	}
	
	
	@Test
	public void beanToJsonTest(){
		Json json=new Json();
		Map<String,Integer> map=Maps.newHashMap();
		List<Integer> list=Lists.newArrayList(1,2,3,4);
		json.setName("张三");
		json.setAge(22);
		json.setMap(map);
		json.setList(list);
		json.setDate(new Date());
		//Assert.assertEquals(jsonStr,JsonUtils.beanToJson(json));
		System.out.println(JsonUtils.beanToJson(json));
	}
	
	
	@Test
	public void jsonToListTest(){
		List<Integer> list=JsonUtils.jsonToList(listStr, Integer.class);
		Assert.assertNotNull(list);
	}
	
	

	
	public static class Json{
		private String name;
		private Integer age;
		private List<Integer> list;
		private Map<String,Integer> map;
		
		private Date date;
		
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
		public List<Integer> getList() {
			return list;
		}
		public void setList(List<Integer> list) {
			this.list = list;
		}
		public Map<String, Integer> getMap() {
			return map;
		}
		public void setMap(Map<String, Integer> map) {
			this.map = map;
		}
		
	}

}
