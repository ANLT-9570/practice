package com.yzl.utils.enums;

public enum AuthorityEnum {
	
	TASK_ISSUED_ADD("sys:rwxf:tj","造林任务下发添加权限"),
	TASK_AUDIT("sys:rwgzz:sh","造林任务审核权限"),
	;
	private AuthorityEnum() {
	}
	private AuthorityEnum(String perms, String mark) {
		this.perms = perms;
		this.mark = mark;
	}
	private String 	perms;//权限标识
	private String mark;//权限名称
	public String getPerms() {
		return perms;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public void setPerms(String perms) {
		this.perms = perms;
	}

	
}
