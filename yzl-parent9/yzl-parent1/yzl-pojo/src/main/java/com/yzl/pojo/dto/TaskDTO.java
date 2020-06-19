package com.yzl.pojo.dto;

import java.math.BigDecimal;

public class TaskDTO {
	
	
	/***
	 * 县名
	 */
	private String county;
	
	/***
	 * 市名
	 */
	private String city;
	
	/***
	 * 任务基数
	 */
	private BigDecimal taskNumber;
	/***
	 * 行政区编码
	 */
	private String aNumber;
	
	
	
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
	public BigDecimal getTaskNumber() {
		return taskNumber;
	}
	public void setTaskNumber(BigDecimal taskNumber) {
		this.taskNumber = taskNumber;
	}
	public String getaNumber() {
		return aNumber;
	}
	public void setaNumber(String aNumber) {
		this.aNumber = aNumber;
	}
	@Override
	public String toString() {
		return "TaskDTO [county=" + county + ", city=" + city + ", taskNumber=" + taskNumber + ", aNumber=" + aNumber
				+ "]";
	}
	
	
	
}
