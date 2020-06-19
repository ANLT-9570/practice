package com.yzl.utils;

import java.util.List;

public class trees {

	private String id;
	private String text;
	private String state;
	private List<trees> children;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<trees> getChildren() {
		return children;
	}
	public void setChildren(List<trees> children) {
		this.children = children;
	}
	
	
}
