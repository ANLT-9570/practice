package com.yzl.planManagementService;

import com.yzl.utils.attrs;

public interface DataPushService {

	String text(String name);
	
	//String taskProgress,taskProgress进度
	//dname地区, epcname工程, tname工程的字段 ,time 时间
	void push(attrs attrs) throws Exception;
}
