package cn.springmvc.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class TestList {
	public static void main(String[] args) {

//		List<String> list = new ArrayList<String>();
//		list.add("1");
//		list.add("2");
//		list.add("3");
//
//		list.get(2);
//		
//		List<Map<String,String>> listss=new ArrayList<>();
//		
//		LinkedList<String> linkedList=new LinkedList<>();
//		linkedList.add("SSS");
		
		
		List<Map<String,String>> list=new ArrayList<>();
		Map<String,String> map=new HashMap<>();
		map.put("shopNo", "shopNo");
		map.put("shopName", "shopName");
		list.add(map);
		
		Map<String,String> map1=new HashMap<>();
		map1.put("shopNo", "shopNo1");
		map1.put("shopName", "shopName1");
		
		list.add(map1);
		System.out.println("testQueryCustomers  success..."+JSON.toJSONString(list));
		
		
	}

}