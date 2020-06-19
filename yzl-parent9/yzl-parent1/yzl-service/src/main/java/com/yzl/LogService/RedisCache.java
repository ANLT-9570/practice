package com.yzl.LogService;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//redis缓存注解
@Target(value=ElementType.METHOD)//作用在方法上
@Retention(value=RetentionPolicy.RUNTIME)
public @interface RedisCache {

	//String cacheKey(); //缓存的key
	
	String type();//类型
	
	//long timeOut(); //有效期
}
