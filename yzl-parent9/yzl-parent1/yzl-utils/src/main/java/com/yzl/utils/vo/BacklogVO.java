package com.yzl.utils.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.aspectj.weaver.ast.And;

public class BacklogVO {
	
	private String name;

	private Date time;
	
	private Integer number;

	private String check;
	
	private String city;

	private String county;
	
	private String stat;
	
	
	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public Date getTime() {
		return time;
	}
	
	public String getUpdateTime() {
		if(time!=null){
			return  new SimpleDateFormat("yyyyƒÍMM‘¬dd»’").format(time);
		}
		return "";
	}

	public void setTime(Date time) {
		System.out.println("time="+time);
		this.time = time;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}



	

}
