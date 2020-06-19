package com.dg.main.service;

import java.util.List;

import com.dg.main.Entity.users.CustomerAddress;
import com.dg.main.util.slzcResult;
import com.dg.main.base.BaseMapper;

public interface CustomerAddressServer extends BaseMapper<CustomerAddress>{

	public slzcResult selectAll(int offset, int limit,String search);
	public CustomerAddress findBy(Long id);
	public void deleteBy(Long id);
	public List<CustomerAddress> selectByUserID(Long userid);
	public int defindUp(Long uid);
}
