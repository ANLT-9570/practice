package com.yzl.utils.enums;

public enum ResultEnum {
	//参数
	PARAM_ERROR(1,"参数不正确"),
	
	PROJECT_ID_ISNULL(2,"工程id为null"),
	
	CITY_IS_NULL(3,"城市名为空"),
	
	COUNTY_IS_NULL(4,"县/区名为空"),
	
	REGION_ID_ISNULL(5,"地区id为空"),
	
	DISTRICT_MARK_ISNULL(6,"地区标识为空"),
	
	AREA_CODE_ISNULL(13,"工程id为null"),
	
	DISTRICT_LIST_IS_NULL(101,"查询到的地区集合为空"),
	
	USER_NOT_MENU(201,"当前用户没有任何权限"),
	
	TASK_BASE_NOT_NUMBER(105,"下发的任务基础不是纯数字"),
	
	LOGIN_USER_NOT_EXIST(104,"当前登录的用户不存在/为空"),
	
	DELETE_PARAM_IS_NULL(105,"需要删除的数据为空"),
	
	TASKBASE_NOT_IS_NUMBER(106,"任务下发基数不是纯数字，可能包含特殊的字符"),

	ZYND_OR_JHND_IS_NULL(107,"作业年度或计划年度的参数值为空"),
	
	COUNTY_OR_CITY_CODE_IS_NULL(108,"市级或县级的行政编号参数值为空"),

	DATA_INSERT_FAILURE(109,"任务数据添加失败"),
	
	DATA_UPDATE_FAILURE(122,"任务数据添加失败"),
	
	COUNTYCODE_PARAM_ERROR(20001,"县级编号错误"),
	
	COUNTYCODE_YEAR_ERROR(20011,"年份为空")
	;
	//地区状态码
	private int code;
	//地区错误信息
	private String message;
	

	
	ResultEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	ResultEnum() {
		
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
