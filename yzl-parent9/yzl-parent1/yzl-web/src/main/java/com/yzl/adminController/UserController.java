package com.yzl.adminController;


import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzl.adminService.FunctionService;
import com.yzl.adminService.IUserService;
import com.yzl.mapper.YzlMenuMapper;
import com.yzl.planManagementController.taskWorkingController;
import com.yzl.pojo.YzlUser;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.LoginUserUtils;
import com.yzl.utils.LoginUtils;
import com.yzl.utils.MD5Utils;
import com.yzl.utils.YzlResult;
import com.yzl.utils.enums.UserEnum;

@Controller
public class UserController {

	@Autowired
	private IUserService userService;
	@Autowired
	private FunctionService fun;
	@Autowired
	private YzlMenuMapper menuMapper;
	/***
	 * 登录功能
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/user/login",method=RequestMethod.GET)
	@ResponseBody
	public YzlResult login(HttpServletRequest httpServletRequest, String username,String password){
		//使用shiro框架提供的方式进行认证
		Subject subject = SecurityUtils.getSubject();//获取当前登录用户对象，现在状态为：未验证
		//用户名密码令牌
		System.out.println("username="+username);
		AuthenticationToken token = new UsernamePasswordToken(username, password);
		try {
			subject.login(token);//验证
		} catch (UnknownAccountException e) {
			return  YzlResult.build(400, e.getMessage());
		}catch (IncorrectCredentialsException e) {
			return YzlResult.build(400, e.getMessage());
		}catch (LockedAccountException e) {
			return YzlResult.build(400, e.getMessage());
		}
		YzlUser loginUser = (YzlUser) subject.getPrincipal();
		httpServletRequest.getSession().setAttribute(UserEnum.LOGIN_USER.getStatus(), loginUser);
		UUID uuid = UUID.randomUUID();
		return YzlResult.ok(uuid.toString());
	}
	/***
	 * 修改密码
	 */
	@RequestMapping(value="/change/password",method=RequestMethod.POST)
	@ResponseBody
    public YzlResult changePaswd(HttpServletRequest httpServletRequest, String oldps, String newps){
		//取当前已登录用户
		YzlUser user = (YzlUser) httpServletRequest.getSession().getAttribute(UserEnum.LOGIN_USER.getStatus());
		//判断用户是否登录
		if(user != null){
			//判断密码是否正确
			if(user.getPassword().equals(oldps)){
				//设置新密码
				user.setPassword(newps);
				//执行更新
				int flag = userService.updatePassword(user);
				if(flag == 1){	
					//把更改后的用户信息重新存入session
					httpServletRequest.getSession().setAttribute(UserEnum.LOGIN_USER.getStatus(), user);
					return YzlResult.ok();
				}
			}
		}
		return YzlResult.build(400, "密码错误！");
	}
	/***
	 * 退出系统
	 */
	@RequestMapping("/user/logout")
	@ResponseBody
	public YzlResult logout(HttpServletRequest httpServletRequest){
		httpServletRequest.getSession().invalidate();
		httpServletRequest.setAttribute(UserEnum.LOGIN_USER.getStatus(), "");
		return YzlResult.ok();
	}
	
	//分页查询
	@RequestMapping("/user/pageQuery")
	@ResponseBody
	public EasyUIResult pageQuery(int rows,int page){
		EasyUIResult result = userService.findAll(page,rows);
		return result;
	}
	
	//添加用户
	@RequestMapping("/user/addUser")
	@ResponseBody
	public String add(YzlUser user){
		//对密码进行MD5加密
		user.setPassword(MD5Utils.md5(user.getPassword()));
		YzlResult result = userService.save(user);
		if(result.getStatus()==200){
			return "";
		}
		return "";
	}
	
	/***
	 * 修改用户
	 * @return
	 */
	@RequestMapping("/user/updateUser")
	public String update(YzlUser user){
		YzlResult result = userService.update(user);
		return "user";
	}
	

}
