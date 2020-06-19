//package com.yzl.LogService;
//
//import java.lang.reflect.Method;
//
//import javax.annotation.Resource;
//
//import org.springframework.cache.interceptor.KeyGenerator;
//import org.springframework.stereotype.Component;
//
//
//@Resource
//public class SpringCacheKeyGenret implements KeyGenerator{
//
//	@Override
//	public Object generate(Object target, Method method, Object... params) {
//		System.out.println(target);
//		String name = method.getName();
//		int length = params.length;
//		
////		method.invoke(obj, args);
//		
//		return null;
//	}
//
//}
