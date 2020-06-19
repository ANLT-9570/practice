package com.yzl.adminService.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzl.adminService.IUserService;
import com.yzl.mapper.YzlUserMapper;
import com.yzl.pojo.YzlRole;
import com.yzl.pojo.YzlUser;
import com.yzl.pojo.YzlUserExample;
import com.yzl.pojo.YzlUserExample.Criteria;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.PageBean;
import com.yzl.utils.YzlResult;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	YzlUserMapper userMapper;

	
	//登录判断
	@Override
	public YzlUser findByUsername(String username) {
		//根据userName查询用户
		YzlUserExample example = new YzlUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<YzlUser> list = userMapper.selectByExample(example);
		//判断是否查询到数据
		if(list.size()>0 && list!=null){
			return list.get(0);
		}
		return null;
	}
	
	
	//用户修改密码
	@Override
	public int updatePassword(YzlUser user){
		return userMapper.updateByPrimaryKey(user);
	}

	//分页查询
	@Override
	public EasyUIResult findAll(int page,int rows) {
		YzlUserExample example = new YzlUserExample();
		Criteria criteria = example.createCriteria();
		//分页查询
		int total = userMapper.countByExample(example);
		Integer start = (page-1)*rows;
	//	List<YzlUser> list = userMapper.findAll(start,Integer.valueOf(rows));	
		//System.out.println("起始页="+start+rows);
//		List<YzlUser> list = userMapper.findAll(start,Integer.valueOf(rows));
		//封装pojo
		EasyUIResult result = new EasyUIResult();
		//result.setRows(list);
		result.setTotal(total);
		return result;
	}


	@Override
	public YzlResult save(YzlUser user) {
		try {
			//设置关联角色关系
			//user.setRoleId(user.getYzlRole().getId());
			//设置状态，默认第一次添加为启动
			//user.setState("1");//1为启动，0为禁用
			user.setStatus("1");//1为启动，0为禁用
			user.setKeywork(UUID.randomUUID().toString());
			user.setStatus("1");//1为启动，0为禁用
			userMapper.insert(user);
		} catch (Exception e) {
			e.printStackTrace();
			return YzlResult.build(400, "数据插入失败");
		}
		return YzlResult.ok();
	}


	@Override
	public YzlResult update(YzlUser user) {
		try {
			//user.setRoleId(user.getYzlRole().getId());
			userMapper.updateByPrimaryKeySelective(user);
		} catch (Exception e) {
			e.printStackTrace();
			return YzlResult.build(400, "数据修改失败");
		}
		return YzlResult.ok();
	}


}
