package com.yzl.utils.enums;

import org.omg.CORBA.PRIVATE_MEMBER;


public enum DistrictEnum {

	REGION_ADMIN("45","广西自治区"),
	
	
	;
	
	
	 DistrictEnum() {
	}
	 DistrictEnum(String code,String name) {
		this.name = name;
		this.code = code;
	}
	/**
	 * 地区名称
	 */
	private String name;
	/***
	 * 行政编号
	 */
	private String code;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	
}
