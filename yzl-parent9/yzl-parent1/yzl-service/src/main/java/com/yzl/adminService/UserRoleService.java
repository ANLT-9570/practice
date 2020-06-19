package com.yzl.adminService;

import java.util.List;

import com.yzl.pojo.YzlUserRole;


public interface UserRoleService {

	//¸ù¾Ýuid²éÑ¯
	List<YzlUserRole> findByUid(String uid);

}
