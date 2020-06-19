package com.dg.main.dao;


import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dg.main.Entity.Menu;
import com.dg.main.Entity.Tree;
import com.dg.main.Entity.User;
import com.dg.main.base.BaseMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


//@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {

	//
	public User findUserbyNameAndPwd(@Param("username") String username,@Param("password") String password);
	public User findUserbyName(String name);
	
	//根据用户名查询
	public User findByUsername(@Param("username") String username);
	
	//查询所有用户
	public ArrayList<User> selectAllUser(@Param("limit") int limit,@Param("offset") int offset,@Param("search")String search);
	//添加用户
	public int addUser(User user);
	
	//用户总计录数
	public long selectCounty(@Param("search")String search);
	
	public List<Tree> findAllRole();
	
	//根据用户id删除
	public int delUserRole(@Param("uid")String uid);
	
	public int addUserRole(@Param("rids")String[] rids, @Param("uid")String uid);
	
	//子节点
	public List<Menu> findByMen(@Param("id")Integer id,@Param("code") String code);
	
	//这个用户拥有的主菜单
	public List<Menu> findByMainMen(@Param("id")Integer id,@Param("code") String code);
	
	public List<Tree> selectByUserRole(@Param("id")Integer id);
	
	public int delUser(@Param("uids")String[] uids);

}
