package com.yzl.utils.dto;

public class DistrictDTO {
	
	private String cityName; 
	private String CountyName;
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCountyName() {
		return CountyName;
	}
	public void setCountyName(String countyName) {
		CountyName = countyName;
	}
	@Override
	public String toString() {
		return "DistrictDTO [cityName=" + cityName + ", CountyName=" + CountyName + "]";
	}
	
}
