package com.dg.main.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dg.main.base.BaseService;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import com.dg.main.Entity.Menu;
import com.dg.main.Entity.Tree;
import com.dg.main.Entity.User;
import com.dg.main.util.slzcResult;
import com.dg.main.base.BaseMapper;


public interface UserService extends BaseService<User> {
	
	//查询所有用户
	public slzcResult findAllUser(int limit,int offset,String search);
	
	//添加用户
	public ModelMap addUser(User user);
	//查询用户拥有的角色
	public List<Tree> findAllRole(String uid);

	public List<Menu> findPms(User user);

	//
	public ModelMap delUser(String [] uids);

	//把用户和角色关联起来
	public ModelMap addUserRole(String[] rids, String uid);

	//文件上传
	public ModelMap upLoadFile(MultipartFile[] file,HttpServletRequest request,HttpServletResponse response); 
}
