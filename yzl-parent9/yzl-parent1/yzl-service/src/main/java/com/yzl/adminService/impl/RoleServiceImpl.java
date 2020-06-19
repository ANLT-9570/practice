package com.yzl.adminService.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzl.adminService.RoleService;
import com.yzl.mapper.YzlDistrictMapper;
import com.yzl.mapper.YzlMenuMapper;
import com.yzl.mapper.YzlRoleMapper;
import com.yzl.mapper.YzlRoleMenuMapper;
import com.yzl.mapper.YzlUserRoleMapper;
import com.yzl.pojo.YzlDistrict;
import com.yzl.pojo.YzlMenu;
import com.yzl.pojo.YzlRole;
import com.yzl.pojo.YzlRoleExample;
import com.yzl.pojo.YzlRoleExample.Criteria;
import com.yzl.pojo.YzlRoleMenu;
import com.yzl.pojo.YzlRoleMenuExample;
import com.yzl.pojo.YzlUserExample;
import com.yzl.pojo.YzlUserRoleExample;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlResult;
import com.yzl.utils.vo.TreeVO;


@Service
@Transactional
public class RoleServiceImpl implements RoleService{

	@Autowired
	YzlRoleMapper roleMapper;
	
	@Autowired
	YzlRoleMenuMapper roleMenuMapper;
	
	@Autowired
	YzlMenuMapper menuMapper;
	@Autowired
	YzlUserRoleMapper userRoleMapper;
	@Autowired 
	YzlDistrictMapper districtMapper;
	
	
	@Override
	public void add(YzlRole role, String functionIds) {
		String[] fids = functionIds.split(",");
		//生成关键字
		role.setCode(UUID.randomUUID().toString());
		
		role.setCreateTine(new Date());
		//插入角色并返回主键
		roleMapper.insert(role);
		if(functionIds!=null && functionIds != ""){
			//获取所有权限id
			for (String fid : fids) {
				//进行表之间关联
				YzlRoleMenu roleMenuKey = new YzlRoleMenu();
				roleMenuKey.setMenuId(Integer.valueOf(fid));
				roleMenuKey.setRoleId(role.getId());
				roleMenuMapper.insert(roleMenuKey);
			}
		}
	}
	@Override
	public EasyUIResult pageQuery(int page, int rows) {
		//设置分页条件
		PageHelper.startPage(page, rows);
		YzlRoleExample example = new YzlRoleExample();
		Criteria criteria = example.createCriteria();
		//执行查询
		List<YzlRole> list = roleMapper.selectByExample(example);
		PageInfo<YzlRole> pageInfo = new PageInfo<>(list);
		//封装返回结果集
		EasyUIResult result = new EasyUIResult();
		result.setTotal((int)pageInfo.getTotal());
		result.setRows(pageInfo.getList());
		return result;
	}
	
	//删除
	@Override
	public void delete(String ids) {
		String[] rid = ids.split(",");
		//遍历数组 获取所有Id
		for (String sid : rid) {
			int id = Integer.valueOf(sid);
			//根据Id循环删除
			roleMapper.deleteByPrimaryKey(id);
			//删除关联权限表
			YzlRoleMenuExample example = new YzlRoleMenuExample();
			com.yzl.pojo.YzlRoleMenuExample.Criteria createCriteria = example.createCriteria();
			createCriteria.andRoleIdEqualTo(id);
			List<YzlRoleMenu> list = roleMenuMapper.selectByExample(example);
			//执行删除
			roleMenuMapper.deleteByExample(example);
			
			//删除角色关联用户表
			YzlUserRoleExample uRExample = new YzlUserRoleExample();
			com.yzl.pojo.YzlUserRoleExample.Criteria uRCreateCriteria = uRExample.createCriteria();
			uRCreateCriteria.andRoleIdEqualTo(id);
			//执行删除
			userRoleMapper.deleteByExample(uRExample);
			
			//删除角色关联权限表
			YzlRoleMenuExample roleMenuExample = new YzlRoleMenuExample();
			com.yzl.pojo.YzlRoleMenuExample.Criteria roleMenuCriteria = roleMenuExample.createCriteria();
			roleMenuCriteria.andRoleIdEqualTo(id);
			//执行删除
			roleMenuMapper.deleteByExample(roleMenuExample);
		}
	}

	
	/***
	 * 根据角色id查询权限
	 */
	@Override
	public YzlResult getFunctionByRoleId(long roleId) {
		List<TreeVO> list = null;
		try {
			list = menuMapper.findFunctionByRoleId(roleId);
		} catch (Exception e) {
			e.printStackTrace();
			return YzlResult.build(400, "数据加载失败！");
		}
		return YzlResult.ok(list);
	}
	
	
	@Override
	public List<YzlRole>  findAll() {
		YzlRoleExample example=new YzlRoleExample();
		Criteria criteria = example.createCriteria();
		//查询所有的角色包括关联的用户
		List<YzlRole> list = roleMapper.selectByExample(example);
		return list;
	}
	
	
	@Override
	public YzlResult update(YzlRole role, String functionIds) {
		System.out.println("cs"+functionIds);
		System.out.println("c"+role);
		//修改角色
		roleMapper.updateByPrimaryKey(role);
		//把旧权限全部删除
		YzlRoleMenuExample example = new YzlRoleMenuExample();
		com.yzl.pojo.YzlRoleMenuExample.Criteria createCriteria = example.createCriteria();
		createCriteria.andRoleIdEqualTo(role.getId());
		roleMenuMapper.deleteByExample(example);
		//新权限进行关联
		if(functionIds != null && functionIds != ""){
			String[] fids = functionIds.split(",");
			for (String fid : fids) {
				YzlRoleMenu roleMenuKey = new YzlRoleMenu();
				roleMenuKey.setRoleId(role.getId());
				roleMenuKey.setMenuId(Integer.valueOf(fid));
				roleMenuMapper.insert(roleMenuKey);
			}
		}
		return null;
	}
	
	@Override
	public YzlRole findByRoleId(Integer roleId) {
		return roleMapper.selectByPrimaryKey(roleId);
	}

}
