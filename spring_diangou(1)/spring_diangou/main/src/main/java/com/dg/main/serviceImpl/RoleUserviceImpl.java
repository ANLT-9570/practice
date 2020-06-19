package com.dg.main.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;


import com.dg.main.Entity.Role;
import com.dg.main.Entity.Tree;
import com.dg.main.util.slzcResult;
import com.dg.main.dao.MenuMapper;
import com.dg.main.dao.RoleMapper;
import com.dg.main.service.RoleUservice;

@Service
public class RoleUserviceImpl implements RoleUservice{

	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private MenuMapper menuMapper;
	
	//所有角色
	public slzcResult findAllRole(int offset,int limit,String search) {
		
		long total = roleMapper.selectCounty(search);
		List<Role> roles = roleMapper.findAllRole(offset,limit,search);
		
		slzcResult result = new slzcResult();
		result.setRows(roles);
		result.setTotal(total);
		
		return result;
	}

	@Override
	public ModelMap addRole(Role role) {
		ModelMap map = new ModelMap();
		//根据名称查询
		Role _role = roleMapper.selectByRoleName(role.getName());
		if(_role!=null) {
			map.put("data",400);
			return map;
		}
		roleMapper.addRole(role);
		map.put("data",200);
		return map;
	}

	@Override
	public ModelMap delRole(String[] rids) {
		ModelMap map = new ModelMap();
		
		//删除角色与菜单关联的表
		roleMapper.delRoleMenu(rids);
		int rs = 0;
		if (rids != null) {
			rs =  roleMapper.delRole(rids);//删除角色列表的角色
		}
		
		if (rs == rids.length) {
			map.put("data",200);
			return map;
		}
		map.put("data",400);
		return map;
	}

	@Override
	public List<Tree> loadTree(String id) {
//		Subject subject = SecurityUtils.getSubject();
//		User user = (User) subject.getSession().getAttribute("user");
		
		// 系统主菜单
		List<Tree> mainTrees = menuMapper.selectBySysMenu("1");
//		List<Tree> userTrees = menuMapper.selectAllMain(Integer.valueOf(id),NumCode.ONE.getCode());
		List<Tree> userTrees = roleMapper.selectByMenu(Integer.valueOf(id),"1");
		mainTrees = treesDispose(mainTrees, userTrees);
		
		// 
		List<Tree> isNT = menuMapper.selectBySysMenu("0");
		List<Tree> userisNT = roleMapper.selectByMenu(Integer.valueOf(id),"0");
		isNT = treesDispose(isNT, userisNT);
		
		
		
		if (mainTrees.size() > 0) {
			for (Tree mainTree : mainTrees) {//主节点编号
				
				if (isNT.size() > 0) {
					
					List<Tree> newTree = new ArrayList<Tree>();//装子节点
					
					for (Tree childrenTree : isNT) {//便利子节点
						
//						String mainCode = mainTree.getId();//主节点编号
						String mainCode = mainTree.getCode();
						String childrenCode = childrenTree.getPid();//子节点的父节点编号
						
						if (mainCode.equals(childrenCode)) {
							newTree.add(childrenTree);
						}
					}
					if (newTree.size() > 0) {
						mainTree.setChildren(newTree);
					}
				}
			}
		}
		return mainTrees;
	}
	
	//树处理
	public List<Tree> treesDispose(List<Tree> sysTree,List<Tree> userTree) {
		for (Tree tree : sysTree) {
			for (Tree ustree : userTree) {
				String id = tree.getId();
				String usid = ustree.getId();
				if (id.equals(usid)) {
					tree.setChecked("true");
				}
			}
		}
		return sysTree;
	}

	@Override
	public Role selectOne(Role t) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int delete(Role t) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int update(Role t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int save(Role t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Role> selectAll(Role t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> selectPageAll(int limit, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteAllId(String [] t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public slzcResult selectAll(int limit, int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectCount() {
		// TODO Auto-generated method stub
		return 0;
	}
}
