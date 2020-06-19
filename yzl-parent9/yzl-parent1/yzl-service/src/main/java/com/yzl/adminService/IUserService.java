package com.yzl.adminService;

import com.yzl.pojo.YzlUser;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlResult;

public interface IUserService {

	YzlUser findByUsername(String username);

	int updatePassword(YzlUser user);

	EasyUIResult findAll(int page,int rows);

	YzlResult save(YzlUser user);

	YzlResult update(YzlUser user);
	

}
