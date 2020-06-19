package com.dg.main.base;

import com.dg.main.util.slzcResult;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseMapper<T> {
	
	public T selectOne(T t);
	
	public List<T> selectAll(T t);

	public slzcResult selectAll(int offset, int limit);//分页

	public List<T> selectPageAll(int offset, int limit);

	public int selectCount();//总记录数
	public int delete(T t);
	public int deleteAllId(String[] t);
	public int update(@Param("t") T t);
	public int save(T t);
	
	//app店铺页面

	
}
