package com.dg.main.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dg.main.Entity.Administrator;
import com.dg.main.base.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorMapper extends BaseMapper<Administrator>{

	List<Administrator> selectPageAll(@Param("offset") int offset,@Param("limit")int limit,@Param("search")String search);
	
	int deleteAllId(@Param("t")String[] t) ;
	
	public int selectCount(@Param("search")String search);
	
	 int update(@Param("t")Administrator t) ;
}
