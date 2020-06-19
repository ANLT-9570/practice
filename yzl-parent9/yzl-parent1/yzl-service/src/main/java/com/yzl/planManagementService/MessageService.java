package com.yzl.planManagementService;

import javax.servlet.http.HttpServletRequest;

import com.yzl.utils.YzlResult;

public interface MessageService {

	//查询当前用户的总记录数
	int getRecordsByUserId(HttpServletRequest request);

	YzlResult updateStatuByUserId();

}
