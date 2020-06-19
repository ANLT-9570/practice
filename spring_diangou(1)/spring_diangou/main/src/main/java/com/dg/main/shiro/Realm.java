package com.dg.main.shiro;

import com.dg.main.Entity.Menu;
import com.dg.main.service.UserService;
import com.dg.main.serviceImpl.UserServiceImpl;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.dg.main.Entity.User;
import com.dg.main.dao.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Realm extends AuthorizingRealm{

	@Resource
	private   UserMapper userMapper;
	private static Map<String,Object> maps = Maps.newHashMap();
	@Autowired
	private UserServiceImpl userService;

	public static Realm realm;

	@PostConstruct
	public void inits() {
		realm = this;

		realm.userMapper = this.userMapper;
		realm.userService = this.userService;
	}


	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		System.out.println("路过授权授权..................");
		User user = (User)arg0.getPrimaryPrincipal();

		List<Menu> pms = realm.userService.findPms(user);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		for(Menu menu:pms){
			info.addStringPermission(menu.getCode());
		}
		return info;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		UsernamePasswordToken authenticationToken =(UsernamePasswordToken) token;
		//获取session
		Session session = SecurityUtils.getSubject().getSession();
		
		Object object = token.getCredentials();
		//转成密码
		String password = null;
		if(object != null) {
			password = new String((char[]) object);
		}
		
		AuthenticationInfo info = null;
		//获取登录的名称
		String username = authenticationToken.getUsername();
//		ModelMap modelMap = userService.addUser(null);
		//根据用户名查询
		User user = realm.userMapper.findByUsername(username);
		
		if(user == null ){
			//用户名不存在
			throw new UnknownAccountException("账号或密码不存在");
		}
		if("2".equals(user.getStatus())) {
			throw new LockedAccountException("账号已被锁定,请联系管理员");
		}
		System.out.println(user.getPassword());
		//密码错误
		if (!password.equals(user.getPassword())) {
			throw new IncorrectCredentialsException("账号或密码不正确");
		}
//		session.setTimeout(666);
		info = new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
		session.setAttribute("user", user);
		return info;
	}

}