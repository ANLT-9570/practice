package com.yzl.adminController;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Objects;
import com.yzl.adminService.RoleService;
import com.yzl.pojo.YzlMenu;
import com.yzl.pojo.YzlRole;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlResult;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzl.adminService.RoleService;
import com.yzl.pojo.YzlRole;
import com.yzl.utils.YzlResult;

@Controller
public class RolesController {

	@Autowired
	private RoleService roleService;

	
	/***
	 * 添加角色
	 * @return
	 */
	@RequestMapping("/role/addRole")
	public String addRole(YzlRole role,String functionIds) {
		try {
			roleService.add(role,functionIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/admin/role";
	}
	
	/**
	 * 分页查询
	 */
	@RequestMapping("/role/pageQuery")
	@ResponseBody
	public EasyUIResult pageQuery(int page,int rows){
		EasyUIResult result=null;
		try {
			result = roleService.pageQuery(page,rows);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//批量删除
	@RequestMapping("/delete/role")
	@ResponseBody
	public YzlResult deleteBatch(String ids){
		try {
			roleService.delete(ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return YzlResult.ok();
	}
	
	/***
	 * 根据角色id查询权限
	 * @return
	 */
	@RequestMapping("/function/getFunctionByRoleId")
	@ResponseBody
	public YzlResult getFunctionByRoleId(long roleId){
		//System.out.println(roleId);
		YzlResult result = roleService.getFunctionByRoleId(roleId);
		return result;
	}
	
	@RequestMapping("/role_add")
	public String role_add() {
		
		return "admin/role_add";
	}
	
	/**
	 * 查询所有的角色
	 */
	@RequestMapping("/role_getRoles")
	@ResponseBody
	public List<YzlRole> findAll() {
		List<YzlRole> list = roleService.findAll();
		return list;
	}
	
	@RequestMapping("/role/updateRole")
	public String updateRole(YzlRole role, String functionIds){
		try {
			YzlResult result = roleService.update(role, functionIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin/role";
	}
}
