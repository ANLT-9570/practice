package com.yzl.utils.enums;

public enum DisEnums {

	NNS(1,"GA"),
	LZS(2,"GB"),
	GLS(3,"GC"),
	WZS(4,"GD"),
	BHS(5,"GE"),
	CZS(6,"GF"),
	LBS(7,"GG"),
	HZS(8,"GJ"),
	YLS(9,"GK"),
	BSS(10,"GL"),
	QZS(11,"GN"),
	FCGS(12,"GP"),
	GGS(13,"GR"),
	GCS(14,"GM"),;
	
	private Integer code;
	private String Mark;
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMark() {
		return Mark;
	}
	public void setMark(String mark) {
		Mark = mark;
	}
	DisEnums(Integer code,String Mark){
		this.code=code;
		this.Mark=Mark;
	}
	DisEnums(){}
}
