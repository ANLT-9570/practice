package com.yzl.utils;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class ObjTOJson {

	
	public static void objToJson(Object o,String[] excludes,HttpServletResponse response){
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		String jsonBean = JSONObject.fromObject(o,jsonConfig).toString();
		try {
			response.setContentType("text/json;charset=utf-8");
			response.getWriter().print(jsonBean);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void listjToJson(List o,String[] excludes,HttpServletResponse response){
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		String jsonBean = JSONArray.fromObject(o,jsonConfig).toString();
		try {
			response.setContentType("text/json;charset=utf-8");
			response.getWriter().print(jsonBean);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String listjToJson(List o,String[] excludes){
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		String jsonBean = JSONArray.fromObject(o,jsonConfig).toString();
		return jsonBean;
	}
}
