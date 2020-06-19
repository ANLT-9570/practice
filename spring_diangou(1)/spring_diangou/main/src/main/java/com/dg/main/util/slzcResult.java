package com.dg.main.util;

import java.io.Serializable;
import java.util.List;

/**
 * easyUIDataGrid对象返回�?
 * <p>Title: slzcResult</p>
 * <p>Description: </p>
 *  
 * @author	
 * @date	
 * @version 
 */
public class slzcResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8973393310458311507L;

	private long total;
	
	private List<?> rows;
	public slzcResult() {}
	public slzcResult(Integer total, List<?> rows) {
		this.total = total;
		this.rows = rows;
	}
	
	public slzcResult(long total, List<?> rows) {
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
