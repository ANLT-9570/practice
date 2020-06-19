package com.yzl.utils.enums;

public enum TaskWorkSatusEnum {
	
	
	TASK_COUNTY_WORK(0,"工作状态中"),
	TASK_CITY_AUDIT(1,"待审核"),
	TASK_CITY_VERIFIED(2,"审核通过并已完成"),
	TASK_PROVINCE_AUDIT(3,"退回")
	;
	
	
	//任务进度状态码
	private Integer code;
	//任务进度信息
	private String msg;

	
	private TaskWorkSatusEnum() {
	}
	private TaskWorkSatusEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	

	
	
	
	
}
