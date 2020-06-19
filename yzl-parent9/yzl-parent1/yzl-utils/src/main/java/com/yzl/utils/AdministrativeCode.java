package com.yzl.utils;


import org.apache.log4j.Logger;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.yzl.utils.enums.ResultEnum;
import com.yzl.utils.exception.YzlException;

public class AdministrativeCode {
	
	
	private static Logger logger = Logger.getLogger(AdministrativeCode.class);
	
	public static String countyCodeConvertCityCode(String countyCode){
		if(StringUtils.isBlank(countyCode)||countyCode.length()<4){
			logger.error("县级行政编号参数错误/为空");
			throw new YzlException(ResultEnum.COUNTYCODE_PARAM_ERROR);
		}
		String cityCode = countyCode.substring(0, 4);//前4位为市级下载编码
		return cityCode;
		
		
	}
}
