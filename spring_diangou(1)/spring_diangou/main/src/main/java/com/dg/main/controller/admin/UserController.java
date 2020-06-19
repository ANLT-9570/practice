package com.dg.main.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dg.main.exception.ExceptionCode;
import com.dg.main.serviceImpl.UserServiceImpl;
import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.dg.main.Entity.Menu;
import com.dg.main.Entity.Tree;
import com.dg.main.Entity.User;
import com.dg.main.util.slzcResult;
import com.dg.main.util.MD5;

@Controller
@Api(tags = "后台-用户后台管理")
@RequestMapping("/userAdmin")
public class UserController {

	@Autowired
	private UserServiceImpl userService;

	@PostMapping("/login")
	@ResponseBody@ApiOperation(value = "findAll",notes = "后台登录")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "username",value = "用户名"),
			@ApiImplicitParam(name = "password",value = "密码")
	})
	public  Result/*Result*/ login(String username,String password,HttpServletRequest request) {
		//使用shiro框架提供的方式进行认证
		Subject subject = SecurityUtils.getSubject();//获取当前登录用户对象，现在状态为：未验证
		//用户名密码令牌
		System.out.println("username="+username);
		
		ModelMap map = new ModelMap();
		try {
			//MD5加密
//			String md5Code = null;
//			try {
//				md5Code = MD5.getMD5Code(password);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			AuthenticationToken token = new UsernamePasswordToken(username, password);
			subject.login(token);//验证
		} catch (UnknownAccountException e) {
			map.put("data", 500);
//			return Result.ok(500);//账号或密码不存在
			return Result.successResult(500);
		}catch (IncorrectCredentialsException e) {
			map.put("data", 600);
//			return Result.ok(600);//账号或密码不正确
			return Result.successResult(600);
		}catch (LockedAccountException e) {
			map.put("data", 700);
//			return Result.ok(700);//账号已被锁定,请联系管理员
			return Result.successResult(700);
		}
		
		User user = (User) subject.getSession().getAttribute("user");
		
		//查询这个用户拥有的权限
		List<Menu> list = userService.findPms(user);
//		SecurityUtils.getSubject().isPermitted();
		request.getSession().setAttribute("list", list);
		map.put("data", 200);
		return Result.successResult();
	}
	
	/**
	 * 查询所有用户
	 * @return
	 */
	@GetMapping("/findAllUser")
	@ResponseBody
	@ApiOperation(value = "findAll",notes = "查询所有")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "offset",value = "第几页"),
			@ApiImplicitParam(name = "limit",value = "每页数量"),
			@ApiImplicitParam(name = "search",value = "查询条件")
	})
	public slzcResult findAllUser(@RequestParam(defaultValue = "15")int limit,@RequestParam(defaultValue = "0")int offset,String search) {
		slzcResult result = userService.findAllUser(limit,offset,search);
		return result;
	}
	
	@GetMapping("/SkipPage")
	public String pageSkip() {
		return "index";
	}
	
	//添加用户
	@PostMapping("/addUser")
	@ResponseBody
	@ApiOperation(value = "addBusiness",notes = "添加")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "user",value = "信息")
	})
	public Result/*Result*/ addUser(User user) {
		//Integer id = userService.addUser(user);
      //  System.out.println(id);
		try {
			user.setPassword(MD5.getMD5Code(user.getPassword()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		ModelMap modelMap=userService.addUser(user);
		//modelMap.put("data",200);
//		System.out.println(map);
//        System.out.println(user);
		return Result.successResult(userService.addUser(user));
	}
	
	//
	@GetMapping("/findAllRoleTree")
	@ResponseBody
	@ApiOperation(value = "findAllRole",notes = "根据角色查询树")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "uid",value = "用户id")
	})
	public Result findAllRole(String uid) {
		List<Tree> trees = userService.findAllRole(uid);
		ModelMap map = new ModelMap();
		map.put("data", trees);
		return Result.successResult(trees);
	}
	
	//把用户和角色关联起来
	@PostMapping("/addUser_role")
	@ResponseBody
	@ApiImplicitParams({
			@ApiImplicitParam(name = "rids",value = "角色id"),
			@ApiImplicitParam(name = "uid",value = "用户id")
	})
	public Result addUserRole(String [] rids,String uid) {
		ModelMap result = userService.addUserRole(rids,uid);
		return Result.successResult(result);
	}
	
	//根据用户id删除
	@DeleteMapping("/delUser")
	@ResponseBody
	@ApiOperation(value = "del",notes = "删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ids",value = "id")
	})
	public Result delUser(String [] uids) {
		ModelMap result = userService.delUser(uids);
		return Result.successResult(result);
	}
	
	@GetMapping("/updateUser")
	@ResponseBody
	@ApiOperation(value = "update",notes = "修改")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "user",value = "信息")
	})
	public Result updateUser(User user) {
		ModelMap map = new ModelMap();
		int su = userService.update(user);
		if (su > 0) {
			map.put("data", 200);
			return Result.successResult();
		}
//		map.put("data", 400);
		return Result.failureResult(ExceptionCode.Failure.ORDER_STATUS_CHANGE);
	}


	@GetMapping("/skipPage")
	public String skipPage() {
		System.out.println("99999");
		return "index";
	}

	@GetMapping("/logins")
	public String logins() {
		return "login";
	}
	
	//文件多上传
//	@PostMapping(value = "/fileUpLoad")
//	@ResponseBody
//	@ApiOperation(value = "upLoadFile",notes = "文件多上传")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "file",value = "文件流")
//	})
//	public Result upLoadFile(@RequestParam("file")MultipartFile[] file,HttpServletRequest request,HttpServletResponse response) {
//		ModelMap result = userService.upLoadFile(file,request,response);
//		return Result.successResult(result);
//	}
	
	@GetMapping("/mod")
	@ResponseBody
	public Result skipsPage() {
		ModelMap map = new ModelMap();
		map.put("123", 123);
		return Result.successResult();
	}
	
	@GetMapping("/userList")
	public String skipsPageUserList() {
		return "userList";
	}
	
	@GetMapping("/dskj")
	public String skipsPageDskj() {
		return "dskj";
	}
	@GetMapping("/text")
	public String skipsPageText() {
		return "test";
	}
	
	//查询这个用户拥有的菜单按钮
	@GetMapping("/userMenu")
	@ResponseBody
	@ApiOperation(value = "userMenu",notes = "查询这个用户拥有的菜单按钮")
	public Result userMenu() {
//		ModelMap map = new ModelMap();
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
		if (user == null) {
//			map.put("list", 400);
			return Result.failureResult(ExceptionCode.Failure.ORDER_STATUS_CHANGE);
		}
		List<Menu> list = userService.findPms(user);
		return Result.successResult(list);
	}
	
	//登录页
	@GetMapping("/login")
	public String skipPageLogin() {
		return "redirect:/login.html";
	}
	
	@GetMapping("/getUser")
	@ResponseBody
	public Result getUser() {
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
		ModelMap map = new ModelMap();
		map.put("data", user);
		return Result.successResult(user);
	}
	
	//登录页
	@GetMapping("/")
	public String skipPages() {
		return "redirect:/login.html";
	}


	@RequestMapping("/unauthorized")
	@ResponseBody
	public Result unauthorized(){
		return Result.failureResult("404","没有这个权限");
	}
}
