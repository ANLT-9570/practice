package com.yzl.utils;

public class attr {

	String OBJECTID;
	String Shape;
	String CITY;
	String COUNTY;
	String TOWN;
	String XIAO_BAN;
	String GCLB;
	String ZLLB;
	String JHND;
	String ZYND;
	String ZLQ_DILEI;
	String DILEI;
	String XTJSBMJ;
	String Shape_Length;
	String Shape_Area;
	String BSM;
	public String getOBJECTID() {
		return OBJECTID;
	}
	public void setOBJECTID(String oBJECTID) {
		OBJECTID = oBJECTID;
	}
	public String getShape() {
		return Shape;
	}
	public void setShape(String shape) {
		Shape = shape;
	}
	public String getCITY() {
		return CITY;
	}
	public void setCITY(String cITY) {
		CITY = cITY;
	}
	public String getCOUNTY() {
		return COUNTY;
	}
	public void setCOUNTY(String cOUNTY) {
		COUNTY = cOUNTY;
	}
	public String getTOWN() {
		return TOWN;
	}
	public void setTOWN(String tOWN) {
		TOWN = tOWN;
	}
	public String getXIAO_BAN() {
		return XIAO_BAN;
	}
	public void setXIAO_BAN(String xIAO_BAN) {
		XIAO_BAN = xIAO_BAN;
	}
	public String getGCLB() {
		return GCLB;
	}
	public void setGCLB(String gCLB) {
		GCLB = gCLB;
	}
	public String getZLLB() {
		return ZLLB;
	}
	public void setZLLB(String zLLB) {
		ZLLB = zLLB;
	}
	public String getJHND() {
		return JHND;
	}
	public void setJHND(String jHND) {
		JHND = jHND;
	}
	public String getZYND() {
		return ZYND;
	}
	public void setZYND(String zYND) {
		ZYND = zYND;
	}
	public String getZLQ_DILEI() {
		return ZLQ_DILEI;
	}
	public void setZLQ_DILEI(String zLQ_DILEI) {
		ZLQ_DILEI = zLQ_DILEI;
	}
	public String getDILEI() {
		return DILEI;
	}
	public void setDILEI(String dILEI) {
		DILEI = dILEI;
	}
	public String getXTJSBMJ() {
		return XTJSBMJ;
	}
	public void setXTJSBMJ(String xTJSBMJ) {
		XTJSBMJ = xTJSBMJ;
	}
	public String getShape_Length() {
		return Shape_Length;
	}
	public void setShape_Length(String shape_Length) {
		Shape_Length = shape_Length;
	}
	public String getShape_Area() {
		return Shape_Area;
	}
	public void setShape_Area(String shape_Area) {
		Shape_Area = shape_Area;
	}
	public String getBSM() {
		return BSM;
	}
	public void setBSM(String bSM) {
		BSM = bSM;
	}
	attr attr;
	
	public attr getAttr() {
		return attr;
	}
	public void setAttr(attr attr) {
		this.attr = attr;
	}
	@Override
	public String toString() {
		return "attr [OBJECTID=" + OBJECTID + ", Shape=" + Shape + ", CITY=" + CITY + ", COUNTY=" + COUNTY + ", TOWN="
				+ TOWN + ", XIAO_BAN=" + XIAO_BAN + ", GCLB=" + GCLB + ", ZLLB=" + ZLLB + ", JHND=" + JHND + ", ZYND="
				+ ZYND + ", ZLQ_DILEI=" + ZLQ_DILEI + ", DILEI=" + DILEI + ", XTJSBMJ=" + XTJSBMJ + ", Shape_Length="
				+ Shape_Length + ", Shape_Area=" + Shape_Area + ", BSM=" + BSM + ", attr=" + attr + "]";
	}
	
}