package com.dg.main.service;

import com.dg.main.Entity.Administrator;
import com.dg.main.base.BaseMapper;
import com.dg.main.util.slzcResult;


public interface AdministratorServer extends BaseMapper<Administrator> {

	public slzcResult selectAll(int offset, int limit,String search) ;

	public int selectCount(String search);
}
