package com.dg.main.util;

import java.util.HashMap;
import java.util.Map;

public class MyCache {

	private static Map<String, Object> map = new HashMap<String, Object>();
	
	public Object getValue(String key) {
		
		Object obj = map.get(key);
		
		if (obj == null) {
			obj = key+",value";
			map.put(key, obj);
		}
		
		return obj;
	}
}
