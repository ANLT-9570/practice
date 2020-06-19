package com.yzl.adminController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzl.adminService.UserRoleService;
import com.yzl.pojo.YzlUserRole;

@Controller
public class UserRoleController {

	@Autowired
	private UserRoleService userRoleService;
	
	@RequestMapping("user_Role")
	@ResponseBody
	public List<YzlUserRole> selectUserRole(String uid){
		
		List<YzlUserRole> uList=userRoleService.findByUid(uid);
		
		return uList;
	}
}
