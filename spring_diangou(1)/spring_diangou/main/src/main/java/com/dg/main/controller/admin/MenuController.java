package com.dg.main.controller.admin;

import java.util.List;

import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.dg.main.Entity.Menu;
import com.dg.main.Entity.Tree;
import com.dg.main.util.slzcResult;
import com.dg.main.service.MenuService;

@Controller
@Api(tags = "后台-菜单按钮")
@RequestMapping("/MenuAdmin")
public class MenuController {

	
	@Autowired
	private MenuService menuService;
	
	/**
	 * 查询所有菜单
	 * @param offset 起始页
	 * @param limit	每页显示的数据条数
	 * @return
	 */
	@GetMapping("/findAllMenu")
	@ResponseBody
	@ApiOperation(value = "findAll",notes = "查询所有")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "offset",value = "第几页"),
			@ApiImplicitParam(name = "limit",value = "每页数量"),
			@ApiImplicitParam(name = "search",value = "查询条件")
	})
	public slzcResult findAllMenu(@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "15")Integer limit,String search) {
		slzcResult result = menuService.findAllMenu(offset,limit,search);
		return result;
	}
	
	//添加菜单
	@PostMapping("/addMenu")
	@ResponseBody
	@ApiOperation(value = "addBusiness",notes = "添加")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "menu",value = "信息")
	})
	public Result addMenu(Menu menu) {
		ModelMap ModelMap = menuService.addMenu(menu);
		return Result.successResult(ModelMap);
	}
	
	//删除
	@DeleteMapping("/delMenu")
	@ResponseBody
	@ApiOperation(value = "del",notes = "删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ids",value = "id")
	})
	public Result del(String [] mids) {
		ModelMap ModelMap = menuService.del(mids);
		return Result.successResult(ModelMap);
	}
	
	//加载树菜单
	@GetMapping("/loadTree")
	@ResponseBody
	@ApiOperation(value = "loadMenu",notes = "加载树菜单")
	public /*List<Menu>*/Result loadMenu(){
		List<Tree> loadTree = menuService.loadTree();
		ModelMap map = new ModelMap();
		map.put("data", loadTree);
		return Result.successResult(loadTree);
	}
	
	//把菜单和角色关联起来1
	@PostMapping("/addMenu_role")
	@ResponseBody
	@ApiOperation(value = "addMenuModelMap",notes = "把菜单和角色关联起来1")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "mids",value = "菜单id"),
			@ApiImplicitParam(name = "rid",value = "角色id")
	})
	public Result addMenuModelMap (String [] mids,String rid) {
		ModelMap ModelMap = menuService.addMenuRole(mids,rid);
		return Result.successResult(ModelMap);
	}
	
	//修改
	@PostMapping("/updateMenu")
	@ResponseBody
	@ApiOperation(value = "update",notes = "修改")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "feedback",value = "信息")
	})
	public Result updateMen(Menu menu) {
		ModelMap ModelMap = menuService.updateMenu(menu);
		return Result.successResult(ModelMap);
	}
	
	//菜单管理页面
	@GetMapping("/menuList")
	public String SkipPageMen() {
		return "menuList";
	}
	
}
