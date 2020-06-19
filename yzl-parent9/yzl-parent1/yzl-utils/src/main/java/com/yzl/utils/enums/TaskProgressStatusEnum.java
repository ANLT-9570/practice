package com.yzl.utils.enums;

public enum TaskProgressStatusEnum {
	//如果任务被退回 则在任务标识末尾 加上 back
	TASKPROGRESS_SEND_BACK("back","任务不合格,上级退回"),
	
	TASKPROGRESS_PROVINCE_CHECK("check","任务待自治区审核"),
	
	TASKPROGRESS_SUCCEED("finish","任务已完成并审核通过"),
	
	TASKPROGRESS_CITY_CHECK("audit","待市级管理员审核"),
	
	TASKPROGRESS_PROVINCE_AUDIT("ROOT","自治区审核权限"),
	;
	//任务进度标识
	private String code;
	//任务进度信息
	private String msg;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	private TaskProgressStatusEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	private TaskProgressStatusEnum() {
	}
	
	
}
