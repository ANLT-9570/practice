package com.yzl.LogService;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.yzl.mapper.YzlDistrictMapper;
import com.yzl.pojo.YzlDistrict;
import com.yzl.pojo.YzlLog;
import com.yzl.pojo.YzlUser;

@Component
@Aspect
public class LogAopAspect {

	@Autowired
	private LogService logService;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private YzlDistrictMapper yzlDistrictMapper;
	
	@Around("@annotation(com.yzl.LogService.LogAnno)")
	public Object aroundAdvice(ProceedingJoinPoint pjp) {
		
		//方法执行前的处理 相当于前置通知
		//获取方法签名
		MethodSignature methodSignature = (MethodSignature)pjp.getSignature();
		
		//获取方法
		Method method = methodSignature.getMethod();
		
		//获取方法上面的注解
		LogAnno logAnno = method.getAnnotation(LogAnno.class);
		
		//获取操作描述的属性值
		String opreateType = logAnno.opreateType();
		
		//创建日志对象
		YzlLog log = new YzlLog();
		
		String[] fileNames = null;
		
		String lea = null;
		//获取上传的文件名称
		String filename = (String) request.getSession().getAttribute("filename");
//		log.setFilename(filename);
		//时间
		String time = null;
		List<HashSet<String>> list = new ArrayList<>();//装hashSet集合的
		
		if ("提交".equals(opreateType)) {
			Object[] args = pjp.getArgs();//获取前端传过来的值
			
			//第一个参数转成数组
			String[] strings =(String[]) args[0];
			fileNames =(String[]) args[8];//多个文件
			
			if (strings != null && strings.length > 0) {
				
				for (String string : strings) {
					
					HashSet<String> hashSet = new HashSet<>();//装工程和地区编号
					
					Gson gson = new Gson();
					
					HashMap<String,String> map = gson.fromJson(string, HashMap.class);//将一个json字符串转成HashMap集合
					Set<Entry<String,String>> entrySet = map.entrySet();
					for (Entry<String, String> entry : entrySet) {
						
						String key = entry.getKey();
						String value = entry.getValue();
						
						if ("county".equals(key)) {//如果为县
							String county = SelectByCounty(value);//得到县的编号
							hashSet.add(county);
						}
						
						if (key.contains("T")) {//如果包含T说明是工程和造林
							String gclb = capture(key);//得到工程类别
							hashSet.add(gclb);
						}
					}
					list.add(hashSet);
				}
			}
			//第二个参数
			String object =(String) args[3];
			if (object != null) {
				
				HashSet<String> hashSet = new HashSet<>();//装工程和地区编号
				
				Gson gson = new Gson();
				
				HashMap<String,String> map = gson.fromJson(object.toString(), HashMap.class);
				
				Set<Entry<String,String>> entrySet = map.entrySet();
				
				for (Entry<String, String> entry : entrySet) {
					
					String key = entry.getKey();
					String value = entry.getValue();
					
					if ("county".equals(key)) {//如果为县
						String county = SelectByCounty(value);//得到县的编号
						hashSet.add(county);
					}
					if (key.contains("T")) {//如果包含T说明是工程和造林
						String gclb = capture(key);//得到工程类别
						hashSet.add(gclb);
					}
				}
				list.add(hashSet);
			}
			//第三个参数    取时间
			time =(String) args[4];
			lea =(String) args[5];
			log.setStat("0");
		}
		if ("审核".equals(opreateType)) {
			//获取前端传过来的值
			Object[] args = pjp.getArgs();
			
			//时间
			time =(String) args[3];
			
			String[] json =(String[]) args[0];//强转成数组
			fileNames =(String[]) args[7];//强转成数组
			
			if (json != null && json.length > 0) {
				for (String string : json) {//遍历集合
					if(string != "" && string!=null) {
						HashSet<String> hashSet = new HashSet<>();//装工程和地区编号
						
						Gson gson = new Gson();
						
						HashMap<String,String> map = gson.fromJson(string, HashMap.class);//将一个json字符串转成HashMap集合
						Set<Entry<String,String>> entrySet = map.entrySet();
						for (Entry<String, String> entry : entrySet) {
							
							String key = entry.getKey();
							String value = entry.getValue();
							
							if ("county".equals(key)) {//如果为县
								String county = SelectByCounty(value);//得到县的编号
								hashSet.add(county);
							}
							
							if (key.contains("T")) {//如果包含T说明是工程和造林
								String gclb = capture(key);//得到工程类别
								hashSet.add(gclb);
							}
						}
						list.add(hashSet);//添加到集合中
					}
					
				}
			}
			//第二个参数
			String object =(String) args[2];
			if (object != null) {
				
				HashSet<String> hashSet = new HashSet<>();//装工程和地区编号
				
				Gson gson = new Gson();
				
				HashMap<String,String> map = gson.fromJson(object.toString(), HashMap.class);
				
				Set<Entry<String,String>> entrySet = map.entrySet();
				
				for (Entry<String, String> entry : entrySet) {
					
					String key = entry.getKey();
					String value = entry.getValue();
					
					if ("county".equals(key)) {//如果为县
						String county = SelectByCounty(value);//得到县的编号
						hashSet.add(county);
					}
					if (key.contains("T")) {//如果包含T说明是工程和造林
						String gclb = capture(key);//得到工程类别
						hashSet.add(gclb);
					}
				}
				list.add(hashSet);
			}
			lea =(String) args[6];
			log.setStat("1");
		}
		
		if ("退回".equals(opreateType) || "自治区退回".equals(opreateType)) {
			//获取前端传过来的值
			Object[] args = pjp.getArgs();
			//时间
			time =(String) args[4];
			//第一个参数转成数组
			String[] strings =(String[]) args[0];
			fileNames =(String[]) args[6];//多个文件
			
			if (strings != null && strings.length > 0) {
				
				for (String string : strings) {
					
					HashSet<String> hashSet = new HashSet<>();//装工程和地区编号
					
					Gson gson = new Gson();
					
					HashMap<String,String> map = gson.fromJson(string, HashMap.class);//将一个json字符串转成HashMap集合
					Set<Entry<String,String>> entrySet = map.entrySet();
					for (Entry<String, String> entry : entrySet) {
						
						String key = entry.getKey();
						String value = entry.getValue();
						
						if ("county".equals(key)) {//如果为县
							String county = SelectByCounty(value);//得到县的编号
							hashSet.add(county);
						}
						
						if (key.contains("T")) {//如果包含T说明是工程和造林
							String gclb = capture(key);//得到工程类别
							hashSet.add(gclb);
						}
					}
					list.add(hashSet);
				}
			}
			//第二个参数
			String object =(String) args[3];
			if (object != null) {
				
				HashSet<String> hashSet = new HashSet<>();//装工程和地区编号
				
				Gson gson = new Gson();
				
				HashMap<String,String> map = gson.fromJson(object.toString(), HashMap.class);
				
				Set<Entry<String,String>> entrySet = map.entrySet();
				
				for (Entry<String, String> entry : entrySet) {
					
					String key = entry.getKey();
					String value = entry.getValue();
					
					if ("county".equals(key)) {//如果为县
						String county = SelectByCounty(value);//得到县的编号
						hashSet.add(county);
					}
					if (key.contains("T")) {//如果包含T说明是工程和造林
						String gclb = capture(key);//得到工程类别
						hashSet.add(gclb);
					}
				}
				list.add(hashSet);
			}
			lea =(String) args[5];
			if ("退回".equals(opreateType)) {
				log.setStat("1");
			}
			if ("自治区退回".equals(opreateType)) {
				log.setStat("2");
			}
		}
		
		if (fileNames!=null) {
			StringBuffer bf = new StringBuffer();
			for (String string : fileNames) {
				bf.append(string+",");
			}
			log.setFilename(bf.toString());
		}
		
		log.setLea(lea);
		log.setOperate(opreateType);//操作说明
		log.setYear(time);//作业年度
		
		YzlUser user = (YzlUser)request.getSession().getAttribute("user");
		log.setName(user.getUsername());//设置操作人
		
		Object result = null;
		
		try {
			//让代理方法执行
			result = pjp.proceed();
			// 2.相当于后置通知(方法成功执行之后走这里)
			//...
		} catch (Throwable e) {
			//相当于异常通知
			e.printStackTrace();
		}finally {
			//相当于最终通知
			Date date = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String format = simpleDateFormat.format(date);
			
			log.setOperateTime(format);//设置操作时间
			
			//遍历
			if (list.size() > 1) {//大于1说明是批量操作
				for (HashSet<String> hashSet : list) {
					
					StringBuffer bf = new StringBuffer();//拼接每一个工程编号
					Iterator<String> iterator = hashSet.iterator();
					while (iterator.hasNext()) {//遍历集合
						String string = iterator.next();
						
						if (string.length()>=6) {//是县的编号
							log.setDcode(string);//设置地区编号
						}else {
							bf.append(string+",");
						}
					}
					log.setGclb(bf.toString());
					logService.addLog(log);//添加日志
				}
			}else {
				if (list.size() != 0) {
					StringBuffer bf = new StringBuffer();
					HashSet<String> hashSet = list.get(0);
					
					Iterator<String> iterator = hashSet.iterator();
					while (iterator.hasNext()) {//遍历集合
						String string = iterator.next();
						
						if (string.length()>=6) {//是县的编号
							log.setDcode(string);
						}else {
							bf.append(string+",");
						}
					}
					log.setGclb(bf.toString());
					logService.addLog(log);//添加日志
				}
				
			}
		}
		request.getSession().removeAttribute("filename");
		return result;
		
	}
	
	//根据县名称查询县编号
	private String SelectByCounty(String county) {
			YzlDistrict district = yzlDistrictMapper.selectByCounty(county);
			String anumber = district.getAnumber();
			return anumber;
	}
	
	//字符串截取得到工程类别
	private String capture(String string) {
		int indexOf = string.indexOf("T");//获取该字符的下标
		String gclb = string.substring(0, indexOf);//截取指定的字符
		return gclb;
	}
}
