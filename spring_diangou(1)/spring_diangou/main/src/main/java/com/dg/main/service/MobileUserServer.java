package com.dg.main.service;


import com.dg.main.Entity.users.MobileUser;
import com.dg.main.util.slzcResult;
import com.dg.main.base.BaseMapper;
import com.dg.main.dao.orders.BaseDao;

public interface MobileUserServer extends BaseMapper<MobileUser> {
	public slzcResult selectAll(int offset, int limit,String search);
	
	public int selectCount(String search) ;

	public int updateImageById(String image, Long mobileUserId);
	
}
