package com.yzl.adminService.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzl.adminService.UserRoleService;
import com.yzl.mapper.YzlUserRoleMapper;
import com.yzl.pojo.YzlUserRole;
import com.yzl.pojo.YzlUserRoleExample;
import com.yzl.pojo.YzlUserRoleExample.Criteria;

@Service
public class UserRoleServiceImpl implements UserRoleService{

	@Autowired
	private YzlUserRoleMapper userRoleMapper;
	
	/**
	 * 根据uid查询
	 */
	@Override
	public List<YzlUserRole> findByUid(String uid) {
		
		//设置查询条件
		YzlUserRoleExample example=new YzlUserRoleExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(Integer.valueOf(uid));
		List<YzlUserRole> list = userRoleMapper.selectByExample(example);
		return list;
	}

}
