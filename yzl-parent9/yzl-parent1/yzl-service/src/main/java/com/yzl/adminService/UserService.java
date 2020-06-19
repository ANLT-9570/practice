package com.yzl.adminService;


import com.yzl.pojo.YzlUser;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlResult;

public interface UserService {

	//添加
	public YzlResult addUser(YzlUser user,String[] rolTd);

	//查询所有
	public EasyUIResult findAll(Integer page,Integer rows);
	
	//根据用户名查询
	YzlUser findByUsername(String username);

	//批量删除
	YzlResult deleteUser(String[] ids);
	
	//修改
	YzlResult updateUser(YzlUser user);

}
