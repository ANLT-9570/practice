package com.yzl.adminService;

import java.util.Collection;
import java.util.List;


import org.apache.commons.lang.StringUtils;
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
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.yzl.mapper.YzlMenuMapper;
import com.yzl.mapper.YzlUserMapper;
import com.yzl.pojo.YzlMenu;
import com.yzl.pojo.YzlRole;
import com.yzl.pojo.YzlUser;

public class BOSRealm  extends AuthorizingRealm {
	
	
	@Autowired
	YzlUserMapper userMapper;
	@Autowired
	private RoleService roleService;
	@Autowired
	private FunctionService functionService;
//	@Autowired
//	private YzlUserRoleMapper userRoleMapper;
	@Autowired
	YzlMenuMapper menuMapper;
	
//	@Autowired
//	private SessionDAO sessionDao;
	
	/*获取授权信息*/
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		//给当前用户授权的权限
		SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
		YzlUser user =(YzlUser) principalCollection.getPrimaryPrincipal();
		//判断是否是系统管理员
		if (user.getUsername().equals("mark")) {
			//查询所有角色
			List<YzlRole> list = roleService.findAll();
			for (YzlRole yzlRole : list) {
				authorizationInfo.addRole(String.valueOf(yzlRole.getCode()));
			}
			//所有的功能权限
			List<YzlMenu> menu = functionService.findAllMenu();
			for (YzlMenu yzlMenu : menu) {
				if (StringUtils.isNotBlank(yzlMenu.getPerms())) {
					authorizationInfo.addStringPermission(yzlMenu.getPerms());
				}
			}
		}else {
			//如果是普通用户
			//查询该用户拥有的角色id
//			Integer uid=Integer.valueOf(String.valueOf(user.getId())); 
			Long id = user.getId();
			List<String> list = menuMapper.selectByUserIdPerms(id.toString());
			for (String perms : list) {
				if (StringUtils.isNotBlank(perms)) {
					authorizationInfo.addStringPermission(perms);
				}
			}
			/*YzlUserRoleExample example=new YzlUserRoleExample();
			example.createCriteria().andUserIdEqualTo(uid);
			List<YzlUserRole> list = userRoleMapper.selectByExample(example);
			
			List<YzlRole> roles = new ArrayList<YzlRole>();
			for (YzlUserRole yzlUserRole : list) {
				Integer roleId = yzlUserRole.getRoleId();
				YzlRole role = roleService.findByRoleId(roleId);//根据角色id查询
				roles.add(role);
			}*/
			//根据用户查询角色
			/*if(roles != null && roles.size()>0) {
				for (YzlRole yzlRole : roles) {
					String code = yzlRole.getCode();
					authorizationInfo.addRole(String.valueOf(yzlRole.getCode()));
					//获取某个角色拥有的功能权限
					Integer id = yzlRole.getId();
					List<YzlMenu> menus = functionService.findFunctionByRoleId(Long.valueOf(id));//根据角色id查询
					for (YzlMenu yzlMenu : menus) {
						if (StringUtils.isNotBlank(yzlMenu.getPerms())) {
							authorizationInfo.addStringPermission(yzlMenu.getPerms());
						}
					}
				}
			}*/
			
		}
		return authorizationInfo;
	}

	
	/**
	 * 认证用户身份
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//System.out.println("realm中的认证方法执行了！");
		UsernamePasswordToken myToken = (UsernamePasswordToken) token;
		Session session = SecurityUtils.getSubject().getSession();
		
		String password = new String((char[]) token.getCredentials());
		
		String username = myToken.getUsername();
//		Object principal = myToken.getPrincipal();
		//根据用户名查询用户
		YzlUser user = userMapper.selectByUsername(username);
//		YzlUserExample example = new YzlUserExample();
//		Criteria criteria = example.createCriteria();
//		criteria.andUsernameEqualTo(username);
		
//		List<YzlUser> list = userMapper.selectByExample(example);
		//System.out.println(list.size());
		if(user == null ){
			//用户名不存在
			throw new UnknownAccountException("账号或密码不存在");
		}
		
		//密码错误
		if (!password.equals(user.getPassword())) {
			throw new IncorrectCredentialsException("账号或密码不正确");
		}
		
		if(user.getStatus().equals("禁用")) {
			throw new LockedAccountException("账号已被锁定,请联系管理员");
		}
		
		//获取在线的sessio
//		Collection<Session> collections = sessionDao.getActiveSessions();
//		for (Session sessions : collections) {
//			System.out.println("登录的用户="+sessions.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));
//			if (sessions.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) != null) {
//				sessions.setTimeout(0);
//				//Subject subject = new Subject().Builder().session(session).buildSubject();
//				break;
//			}
//		}
		
//		YzlUser user = list.get(0);
//		Long id = user.getId();
//		String string = user.getUsername();
		
		session.setTimeout(7200000);//设置过期时间
//		session.setTimeout(120000);
		session.setAttribute("user", user);
		
//		session.setAttribute(id+string, id+string);
		AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
		YzlUser object = (YzlUser) session.getAttribute("user");
		return info;
	}
}
