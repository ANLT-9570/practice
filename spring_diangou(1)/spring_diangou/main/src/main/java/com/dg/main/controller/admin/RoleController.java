package com.dg.main.controller.admin;

import java.util.List;

import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.dg.main.Entity.Role;
import com.dg.main.Entity.Tree;
import com.dg.main.util.slzcResult;
import com.dg.main.service.RoleUservice;

@Controller
@Api(tags = "后台-角色")
@RequestMapping("/RoleAdmin")
public class RoleController {

	@Autowired
	private RoleUservice roleUserver;
	
	//所有角色
	@GetMapping("/findAllRole")
	@ResponseBody
	@ApiOperation(value = "findAllRole",notes = "查询所有")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "offset",value = "第几页"),
			@ApiImplicitParam(name = "limit",value = "每页数量"),
			@ApiImplicitParam(name = "search",value = "查询条件")
	})
	public slzcResult findAllRole(@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "15")Integer limit,String search) {
		return roleUserver.findAllRole(offset,limit,search);
	}
	
	//添加角色
	@PostMapping("/addRole")
	@ResponseBody
	@ApiOperation(value = "addRole",notes = "添加")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "role",value = "信息")
	})
	public Result addRole(Role role) {
		
		return Result.successResult(roleUserver.addRole(role));
	}
	
	@DeleteMapping("/delrole")
	@ResponseBody
	@ApiOperation(value = "delRole",notes = "删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "rids",value = "id")
	})
	public Result delRole(String [] rids) {
		ModelMap result = roleUserver.delRole(rids);
		return Result.successResult(result);
	}
	
	//加载树
	@GetMapping("/loadTree")
	@ResponseBody
	@ApiOperation(value = "loadMenu",notes = "加载树")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "rid",value = "角色id")
	})
	public Result loadMenu(String rid){
		List<Tree> loadTree = roleUserver.loadTree(rid);
		ModelMap map = new ModelMap();
		map.put("data", loadTree);
		return Result.successResult(loadTree);
	}
	//页面跳转
	@GetMapping("/roleList")
	public String SkipPage() {
		return "roleList";
	}
}
