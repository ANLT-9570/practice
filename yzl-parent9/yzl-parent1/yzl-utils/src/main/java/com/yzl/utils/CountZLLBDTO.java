package com.yzl.utils;

public class CountZLLBDTO {
	
	private String city;
	private String county;
	private String ZLLB;
	private String XTJSBMJ;
	
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
	public String getZLLB() {
		return ZLLB;
	}
	public void setZLLB(String zLLB) {
		ZLLB = zLLB;
	}
	public String getXTJSBMJ() {
		return XTJSBMJ;
	}
	public void setXTJSBMJ(String xTJSBMJ) {
		XTJSBMJ = xTJSBMJ;
	}
	@Override
	public String toString() {
		return "CountZLLBDTO [city=" + city + ", county=" + county + ", ZLLB=" + ZLLB + ", XTJSBMJ=" + XTJSBMJ + "]";
	}

	
	
}
