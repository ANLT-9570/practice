package com.dg.main.Entity;

import java.io.Serializable;
import java.util.List;


public class Tree implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2121914650372293284L;
	private String id;
	private String code;
	private String pid;
	private String name;
	private String open = "close";//默认关闭
	private List<Tree> children;
	private String checked;
	
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public List<Tree> getChildren() {
		return children;
	}
	public void setChildren(List<Tree> children) {
		this.children = children;
	}
	
	
}
