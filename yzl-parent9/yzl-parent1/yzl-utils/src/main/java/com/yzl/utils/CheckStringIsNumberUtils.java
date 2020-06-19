package com.yzl.utils;

import java.util.regex.Pattern;

public class CheckStringIsNumberUtils {
	
	
	  public static boolean isInteger(String str) {  
		  try {
			int i = Integer.parseInt(str);  //转换成功则为数字，
		} catch (Exception e) {
			return false;
		}
		  return true;
	  }
	  
	  
	  public static boolean isDecimals(String strNumber){
		  try {
			double d = Double.parseDouble(strNumber);  //转换成功则为数字，
		} catch (Exception e) {
			return false;
		}
		  return true;
	  }
	  
	  public static boolean isFloat(String strNumber){
		  try {
			float d = Float.valueOf(strNumber);  //转换成功则为数字，
		} catch (Exception e) {
			return false;
		}
		  return true;
	  }
}
