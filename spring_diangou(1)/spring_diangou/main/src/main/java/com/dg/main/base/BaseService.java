package com.dg.main.base;

public interface BaseService <T> {
	public T selectOne(T t);
	public T selectAll(T t);
	public int delete(T t);
	public int deleteAllId(T t);
	public int update(T t);
	public int save(T t);
}
