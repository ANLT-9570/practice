package com.yzl.utils;

public class YzlEpcAndTaskStaticti {

	
	 private String city;//市
	 private String county;//县
	 private String GCLB;//工程编号
	 private String ZLLB;//造林编号
	 private String JHND;//计划时间
	 private String ZYND;//作业时间
	 private String stat;//状态
	 private String XTJSBMJ;//任务情况
	 
	 private String subtotal;//计
	 private String HSZL;//荒山人工造林
	 private String FSYL;//封山育林
	 private String JDGX;//迹地人工更新造林
	 private String DXGZ;//低效林改造
	 private String TGHL;//退耕地还林
	 private String LXZZ;//林下种植中药材
	 
	 
	 
	public String getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}
	public String getHSZL() {
		return HSZL;
	}
	public void setHSZL(String hSZL) {
		HSZL = hSZL;
	}
	public String getFSYL() {
		return FSYL;
	}
	public void setFSYL(String fSYL) {
		FSYL = fSYL;
	}
	public String getJDGX() {
		return JDGX;
	}
	public void setJDGX(String jDGX) {
		JDGX = jDGX;
	}
	public String getDXGZ() {
		return DXGZ;
	}
	public void setDXGZ(String dXGZ) {
		DXGZ = dXGZ;
	}
	public String getTGHL() {
		return TGHL;
	}
	public void setTGHL(String tGHL) {
		TGHL = tGHL;
	}
	public String getLXZZ() {
		return LXZZ;
	}
	public void setLXZZ(String lXZZ) {
		LXZZ = lXZZ;
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
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public String getXTJSBMJ() {
		return XTJSBMJ;
	}
	public void setXTJSBMJ(String xTJSBMJ) {
		XTJSBMJ = xTJSBMJ;
	}
	@Override
	public String toString() {
		return "YzlEpcAndTaskStaticti [city=" + city + ", county=" + county + ", GCLB=" + GCLB + ", ZLLB=" + ZLLB
				+ ", JHND=" + JHND + ", ZYND=" + ZYND + ", stat=" + stat + ", XTJSBMJ=" + XTJSBMJ + ", subtotal="
				+ subtotal + ", HSZL=" + HSZL + ", FSYL=" + FSYL + ", JDGX=" + JDGX + ", DXGZ=" + DXGZ + ", TGHL="
				+ TGHL + ", LXZZ=" + LXZZ + "]";
	}
	
}
