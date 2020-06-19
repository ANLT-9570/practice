package com.dg.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dg.main.Entity.Role;
import com.dg.main.Entity.Tree;
import com.dg.main.base.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMapper extends BaseMapper<Role>{

	//所以角色
	List<Role> findAllRole(@Param("offset")int offset,@Param("limit") int limit,@Param("search")String search);

	//角色统计
	long selectCounty(@Param("search")String search);

	//根据角色名称查询
	public Role selectByRoleName(@Param("name")String name);

	//添加角色
	int addRole(Role role);

	int delRole(@Param("rids")String[] rids);

	//根据角色查询菜单
	List<Tree> selectByMenu(@Param("id")Integer id, @Param("code")String code);

	//删除角色与菜单关联的表
	void delRoleMenu(@Param("rids")String[] rids);

	
}
