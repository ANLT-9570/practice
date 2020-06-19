package com.yzl.utils.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hp.hpl.sparta.xpath.ThisNodeTest;

/**
 * @author administrator_
 *
 */
public class DrilldownNode {
	
	private String id;
	private String name;
	private double y;
	
	
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	private List<List> data;
	


	public List<List> getData() {
		return data;
	}
	public void setData(List<List> data) {
		this.data = data;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "DrilldownNode [id=" + id + ", name=" + name + ", data=" + data + "]";
	}







	
	

}
