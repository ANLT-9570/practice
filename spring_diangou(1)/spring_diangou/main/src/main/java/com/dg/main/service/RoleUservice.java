package com.dg.main.service;

import java.util.List;

import org.springframework.ui.ModelMap;

import com.dg.main.Entity.Role;
import com.dg.main.Entity.Tree;
import com.dg.main.util.slzcResult;
import com.dg.main.base.BaseMapper;

public interface RoleUservice extends BaseMapper<Role> {

	//���н�ɫ
	public slzcResult findAllRole(int offset,int limit,String search) ;

	//��ӽ�ɫ
	public ModelMap addRole(Role role);

	public ModelMap delRole(String[] rids);

	public List<Tree> loadTree(String id);
	
	
}
