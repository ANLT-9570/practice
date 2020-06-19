package com.yzl.LogService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzl.LogService.LogService;
import com.yzl.mapper.YzlLogMapper;
import com.yzl.pojo.YzlLog;

@Service
public class LogServiceImpl implements LogService{

	@Autowired
	private YzlLogMapper logMapper;
	
	@Override
	public boolean addLog(YzlLog log) {
		return logMapper.insert(log) > 0 ? true:false;
	}

}
