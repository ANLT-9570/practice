package com.yzl.utils.enums;

public enum KeyNameEnum {

	county("county"),//县
	JHND("JHND"),//计划年度
	ZYND("ZYND"),//作业年度
	CITY_CODE("cityCode"),
	JH("jh"),//计划
	ZJH("zjh"),//计划
	WC("wc"),//计划
	
	;
	private String name;

	
	
	private KeyNameEnum(String name) {
		this.name = name;
	}	


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	
	
	
}
