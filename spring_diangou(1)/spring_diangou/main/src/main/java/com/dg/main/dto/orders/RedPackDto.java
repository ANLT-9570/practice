package com.dg.main.dto.orders;


import com.dg.main.Entity.orders.RedPack;

public class RedPackDto extends RedPack {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6381002775913521458L;
	
	private String CountRedMoney;//红包总金额

	public String getCountRedMoney() {
		return CountRedMoney;
	}

	public void setCountRedMoney(String countRedMoney) {
		CountRedMoney = countRedMoney;
	}
	
}
