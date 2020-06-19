package com.yzl.LogService;

import com.yzl.pojo.YzlLog;

public interface LogService {

	
	/**
	 * 增加日志
	 * @param log
	 * @return
	 */
	public boolean addLog(YzlLog log);
}
