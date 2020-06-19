package com.dg.main.serviceImpl;

import java.util.List;


import com.dg.main.Entity.users.MobileUser;
import com.dg.main.dao.SaleMapper;
import com.dg.main.service.SaleService;
import com.dg.main.util.slzcResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class SaleServiceImpl implements SaleService {

	
//	@Autowired
	@Resource
	private SaleMapper mappers;
	

	@Override
	public slzcResult selectAll(int offset, int limit, String search) {
		int count = mappers.selectCount();
		List<MobileUser> list =  mappers.selectPageAll(offset, limit,search);
		
		slzcResult result = new slzcResult();
		result.setRows(list);
		result.setTotal(count);
		
		return result;
	}

}
