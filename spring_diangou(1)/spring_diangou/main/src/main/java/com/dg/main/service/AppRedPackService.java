package com.dg.main.service;


import com.dg.main.util.Result;

public interface AppRedPackService {
	
	public Result selectAppRedPack();

    Result bestNewRecord(Integer userId);
}
