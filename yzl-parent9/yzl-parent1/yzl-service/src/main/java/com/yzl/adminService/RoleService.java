package com.yzl.adminService;

import java.util.List;

import com.yzl.pojo.YzlRole;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlResult;

import com.yzl.utils.YzlResult;

public interface RoleService {

	void add(YzlRole role, String functionIds);

	EasyUIResult pageQuery(int page, int rows);

	void delete(String ids);

	YzlResult getFunctionByRoleId(long roleId);
	
	 List<YzlRole> findAll();
	//查询所有角色

	YzlResult update(YzlRole role, String ids);

	YzlRole findByRoleId(Integer roleId);

	
}
