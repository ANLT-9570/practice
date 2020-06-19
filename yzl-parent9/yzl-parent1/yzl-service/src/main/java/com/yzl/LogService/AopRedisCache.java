package com.yzl.LogService;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.enterprise.inject.New;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.yzl.mapper.YzlDistrictMapper;
import com.yzl.pojo.YzlDistrict;
import com.yzl.pojo.YzlDistrictExample;
import com.yzl.pojo.YzlDistrictExample.Criteria;
import com.yzl.utils.EasyUIResult;

//import redis.clients.jedis.Jedis;

@Component
@Aspect
public class AopRedisCache {

//	@Autowired
//	private Jedis jedis;
	
//	@Pointcut("execution(public * com.yzl.*Service..*.*(..))")//切入点
//	public void aopRedis() {
		
//	}
	@Autowired
	private YzlDistrictMapper districtMapper;
	
	@Resource
	private RedisUtils redisUtils;

	@Around("@annotation(com.yzl.LogService.RedisCache)")//环绕通知,在目标方法完成前后做增强处理
	public Object aroundAdvice(ProceedingJoinPoint joinPoint) {
		
		//方法执行前的处理 相当于前置通知
		//获取方法签名
		MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
		
		//获取方法
		Method method = methodSignature.getMethod();
		
		//获取方法上面的注解
		RedisCache redisCache = method.getAnnotation(RedisCache.class);
		
		//获取操作描述的属性值
		String opreateType = redisCache.type();
		
		//获得jedis对象
//		Jedis jedis = JedisPoolClient.getJedis();
		Object[] args = joinPoint.getArgs();
		String disCode = null;//地区
		String username = null;//登录的用户名
		String gclb=null;
		Integer page = null;
		String time = null;
		String condition = null;
		String zllb = null;
		String cityCode = null;
		String Mon_zllb = null;
		String Mon_gclb = null;
		
		if (!opreateType.equals("taskIssued") && !opreateType.contains("mon") && !opreateType.equals("delIsuueFile")) {
			disCode =(String) args[1];//获取第二个参数地区的编号
			username = (String) args[2];//获取第三个参数当前登录的用户名
		}
		
		
//		System.out.println(disCode+"-------------");
		if (disCode !=null && disCode.length()==2) {
			YzlDistrictExample example = new YzlDistrictExample();
			Criteria criteria = example.createCriteria();
			criteria.andFlagEqualTo(disCode);
			List<YzlDistrict> district = districtMapper.selectByExample(example);
			disCode = district.get(0).getCitycode();
		}
		String stat = null;
		if(opreateType.equals("taksWorkadd")) {//任务工作中
			time =(String) args[0];//时间
			gclb =(String) args[3];//工程
			stat =(String) args[6];//状态
			//先从缓存中取数据
			String CacheData = (String) redisUtils.get(disCode+"taskwork"+username+time+gclb+stat);
			if(StringUtils.isNotBlank(CacheData)) {//判断数据是否为空
				//如果不为空直接从缓存区取数据   把数据返回
				EasyUIResult result =(EasyUIResult) JSONObject.parseObject(CacheData, EasyUIResult.class);
				return result;
			}
			
		}else if(opreateType.equals("completionTask")) {//完成
			time =(String) args[0];//时间
			page = (Integer) args[3];
			zllb = (String) args[5];//造林类别
			//先从缓存中取数据
			String CacheData = (String) redisUtils.get(disCode+"completion"+username+time+page+zllb);
			if(StringUtils.isNotBlank(CacheData)) {//判断数据是否为空
				//如果不为空直接从缓存区取数据   把数据返回
				EasyUIResult result =(EasyUIResult) JSONObject.parseObject(CacheData, EasyUIResult.class);
				return result;
			}
		}else if (opreateType.equals("monitMunicipality")) {//监测统治自治区
//			String time1 =(String) args[0];//时间
			disCode = "GX450";
			condition =(String) args[1];//条件
			time =(String) args[2];//时间
			
			if ((String) args[0]!=null) {
				time = (String) args[0];
			}
			page =(Integer) args[3];//页码
			username =(String) args[5];//用户名show_xbMunicipality
			cityCode = (String) args[6];//市编号
			Mon_zllb = (String) args[7];//造林类别
			Mon_gclb = (String) args[8];//造林类别
			//先从缓存中取数据
			String CacheData = (String) redisUtils.get(disCode+"monitMunicipality"+username+time+page+condition+cityCode+Mon_zllb+Mon_gclb);
			if(StringUtils.isNotBlank(CacheData)) {//判断数据是否为空
				//如果不为空直接从缓存区取数据   把数据返回
				EasyUIResult result =(EasyUIResult) JSONObject.parseObject(CacheData, EasyUIResult.class);
				return result;
			}
		}else if (opreateType.equals("monitCity")) {//监测统治市
			disCode =(String) args[0];//地区编号
			YzlDistrict district = districtMapper.selectByFlag(disCode);
			disCode = district.getCitycode();
			page =(Integer) args[1];//页码
			condition =(String) args[3];//条件
			
			time =(String) args[5];//时间
			if ((String) args[4]!=null) {
				time =(String) args[4];//时间
			}
			username =(String) args[6];//用户名
			cityCode = (String) args[7];//市编号
			Mon_zllb = (String) args[8];//造林类别
			Mon_gclb = (String) args[9];//造林类别
			
			//先从缓存中取数据
			String CacheData = (String) redisUtils.get(disCode+"monitCity"+username+time+page+condition+cityCode+Mon_zllb+Mon_gclb);
			if(StringUtils.isNotBlank(CacheData)) {//判断数据是否为空
				//如果不为空直接从缓存区取数据   把数据返回
				EasyUIResult result =(EasyUIResult) JSONObject.parseObject(CacheData, EasyUIResult.class);
				return result;
			}
		}else if (opreateType.equals("monitCounty")) {//监测统治县
			disCode =(String) args[0];//地区编号
			
			YzlDistrict district = districtMapper.selectByPrimaryKey(Integer.valueOf(disCode));
			disCode = district.getAnumber();
			
			page =(Integer) args[1];//页码
			condition =(String) args[3];//条件
			
			time =(String) args[5];//时间
			if ((String) args[4]!=null) {
				time =(String) args[4];//时间
			}
			username =(String) args[6];//用户名
			Mon_zllb = (String) args[7];//造林类别
			Mon_gclb = (String) args[8];//造林类别
			//先从缓存中取数据
			String CacheData = (String) redisUtils.get(disCode+"monitCounty"+username+time+page+condition+Mon_zllb+Mon_gclb);
			if(StringUtils.isNotBlank(CacheData)) {//判断数据是否为空
				//如果不为空直接从缓存区取数据   把数据返回
				EasyUIResult result =(EasyUIResult) JSONObject.parseObject(CacheData, EasyUIResult.class);
				return result;
			}
		}
		
		//如果为下发任务
		if (opreateType.equals("taskIssued")) {
			page =(Integer) args[1];//页码
			time =(String) args[3];//时间
			disCode =(String) args[4];//地区
			 gclb =(String) args[5];//工程类别
			username = (String) args[6];//用户名
			if (disCode.equals("45")) {
				disCode = "GX450";
			}
			//先从缓存中取数据
			String CacheData = (String) redisUtils.get(disCode+"taskIssued"+username+time+gclb+page);
			if(StringUtils.isNotBlank(CacheData)) {//判断数据是否为空
				//如果不为空直接从缓存区取数据   把数据返回
				EasyUIResult result =(EasyUIResult) JSONObject.parseObject(CacheData, EasyUIResult.class);
				return result;
			}
		}
		
		if (opreateType.equals("taksWorkdelIsuuedUpdate")) {
			disCode =(String) args[2];//地区
			
			YzlDistrict district = districtMapper.selectByCounty(disCode);
			String string = district.getCitycode();
			Set<String> keys =  (Set<String>) redisUtils.keys("*");
			if (keys.size() > 0) {
				for (String city : keys) {
					if(city.contains(string)) {
						redisUtils.del(city);
					}
					if (city.contains("GX45")) {
						 redisUtils.del(city);
					}
				}
			}
		}
		
		LinkedHashSet<String> hashSet = new LinkedHashSet<>();
		if (opreateType.equals("taksWorkdelIsuued") ) {//删除key
			
			HashMap<String,Object> hashMap = new HashMap<>();
			
			if (disCode.equals("") || disCode == null) {
				String [] array =(String[]) args[0];//地区编号
				for (String string : array) {
					hashMap = JSONObject.parseObject(string, HashMap.class);
					Object object = hashMap.get("cityCode");
					hashSet.add(object.toString());
				}
			}else {
				 hashMap = JSONObject.parseObject(disCode, HashMap.class);
				 Object object = hashMap.get("cityCode");
				 hashSet.add(object.toString());
			}
			
		}
		
		if (opreateType.equals("taksWorkdel") || opreateType.equals("taksWorkdelIsuued")) {//删除key
			if (disCode != null && disCode.length() == 6 &&  disCode != "null") {
				disCode = disCode.substring(0, 4);
				System.out.println(disCode);
			}
			Set<String> keys =  (Set<String>) redisUtils.keys("*");
			if (keys != null && keys.size()>0) {
				for (String string : keys) {
					if (hashSet != null && hashSet.size() > 0) {
						for (String city : hashSet) {
							if(string.contains(city)) {
								redisUtils.del(string);
							}
						}
					}
					if(string.contains(disCode)) {
						redisUtils.del(string);
					}
					if (string.contains("GX45")) {
						 redisUtils.del(string);
					}
					if (string.contains("taskIssued")) {
						redisUtils.del(string);
					}
				}
			}
		}
		if (opreateType.equals("delIsuueFile")) {
			
			Set<String> keys =  (Set<String>) redisUtils.keys("*");
			for (String string : keys) {
				if (string.contains("taskIssued")) {
					redisUtils.del(string);
				}
			}
		}
		
		Object object = null;
		
		try {
			
			//缓存中没有数据直接执行代理方法查询数据库
			//执行代理方法
			object = joinPoint.proceed();
			
			if (object!=null && opreateType.equals("taksWorkadd")) {
				String jsonString = JSONObject.toJSONString(object);
				//使用地区编号跟登录的用户名作为key
				redisUtils.set(disCode+"taskwork"+username+time+gclb+stat, jsonString);
			}else if(opreateType.equals("completionTask")) {
				String jsonString = JSONObject.toJSONString(object);
				//使用地区编号跟登录的用户名作为key
				redisUtils.set(disCode+"completion"+username+time+page+zllb, jsonString);
			}else if(opreateType.equals("taskIssued")) {
				String jsonString = JSONObject.toJSONString(object);
				//使用地区编号跟登录的用户名作为key
				redisUtils.set(disCode+"taskIssued"+username+time+gclb+page, jsonString);
			}else if (opreateType.equals("monitMunicipality")) {
				
				String jsonString = JSONObject.toJSONString(object);
				//使用地区编号跟登录的用户名作为key
				redisUtils.set(disCode+"monitMunicipality"+username+time+page+condition+cityCode+Mon_zllb+Mon_gclb, jsonString);
			}else if(opreateType.equals("monitCity")){
				String jsonString = JSONObject.toJSONString(object);
				//使用地区编号跟登录的用户名作为key
				redisUtils.set(disCode+"monitCity"+username+time+page+condition+cityCode+Mon_zllb+Mon_gclb, jsonString);
			}else if (opreateType.equals("monitCounty")) {
				String jsonString = JSONObject.toJSONString(object);
				//使用地区编号跟登录的用户名作为key
				redisUtils.set(disCode+"monitCounty"+username+time+page+condition+Mon_zllb+Mon_gclb, jsonString);
			}
			
			
		} catch (Throwable e) {
			e.printStackTrace();
		}finally {
		}
		return object;
	}

	//判断是否是字母
	public boolean check(String fstrData){
        char   c   =   fstrData.charAt(0);
        
        if(((c>='a'&&c<='z')   ||   (c>='A'&&c<='Z'))){  
              return   true;   
        }else{
              return   false;   
        }   
    }
	//判断是否为数字
	public boolean isNumeric(String str){
	    Pattern pattern = Pattern.compile("[0-9]*");
	    return pattern.matcher(str).matches();   
	 }
}