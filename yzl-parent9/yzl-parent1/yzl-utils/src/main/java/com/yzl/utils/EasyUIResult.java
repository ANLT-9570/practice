package com.yzl.utils;

import java.io.Serializable;
import java.util.List;

/**
 * easyUIDataGrid对象返回�?
 * <p>Title: EasyUIResult</p>
 * <p>Description: </p>
 *  
 * @author	
 * @date	
 * @version 
 */
public class EasyUIResult implements Serializable{

	private long total;
	
	private List<?> rows;
	public EasyUIResult() {}
	public EasyUIResult(Integer total, List<?> rows) {
		this.total = total;
		this.rows = rows;
	}
	
	public EasyUIResult(long total, List<?> rows) {
		this.total = (int) total;
		this.rows = rows;
	}

	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
}
