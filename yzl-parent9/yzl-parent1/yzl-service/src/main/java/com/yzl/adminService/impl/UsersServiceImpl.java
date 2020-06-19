package com.yzl.adminService.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzl.adminService.UserService;
import com.yzl.mapper.YzlRoleMapper;
import com.yzl.mapper.YzlUserMapper;
import com.yzl.mapper.YzlUserRoleMapper;
import com.yzl.pojo.YzlUser;
import com.yzl.pojo.YzlUserExample;
import com.yzl.pojo.YzlUserExample.Criteria;
import com.yzl.pojo.YzlUserRole;
import com.yzl.pojo.YzlUserRoleExample;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.IDUtils;
import com.yzl.utils.MD5Utils;
import com.yzl.utils.YzlResult;

@Transactional
@Service
public class UsersServiceImpl implements UserService{

	//注入用户mapper
	@Autowired
	private YzlUserMapper userMapper;
	
	//注入用户角色mapper
	@Autowired
	private YzlUserRoleMapper userRoleMapper;

	//注入角色mapper
//	@Autowired
//	private YzlRoleMapper roleMapper;
	
	//添加用户
	@Override
	public YzlResult addUser(YzlUser user,String [] rolTd) {
		
		YzlUser byUsername = this.findByUsername(user.getUsername());
		if(byUsername!=null) {
			return YzlResult.build(400, "用户名已存在");
		}
		//数据封装
		String pa = MD5Utils.md5(user.getPassword());
		user.setPassword(pa);
		user.setCreateTime(new Date());
		user.setKeywork(IDUtils.getKeyWork());//设置关键字
		//插入数据库
		int insert = userMapper.insert(user);
		int isOk=0;
		if(rolTd != null && rolTd.length > 0) {
			for (String rolid : rolTd) {
				//角色与用户关联
				YzlUserRole record=new YzlUserRole();
				record.setRoleId(Integer.valueOf(rolid));
				record.setUserId(Integer.valueOf(String.valueOf(this.findByUsername(user.getUsername()).getId())));
				isOk = userRoleMapper.insert(record);
			}
		}
		if (insert > 0 && isOk > 0) {
			return YzlResult.ok(200);
		}
		return null;
	}

	//查询所有用户
	@Override
	public EasyUIResult findAll(Integer page,Integer rows) {
		if (page==null) {
			page=1;
		}
		if (rows==null) {
			rows=10;
		}
		PageHelper.startPage(page, rows);
		YzlUserExample example=new YzlUserExample();
		
		//查询所有用户
		List<YzlUser> list = userMapper.selectByExample(example);
		
		
		for(int i=0;i<list.size();i++) {
			
			YzlUser yzlUser = list.get(i);
			//日期转换
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = yzlUser.getCreateTime();
			if(date==null) {
				date=new Date();
			}
			String str=simpleDateFormat.format(date);
			yzlUser.setCreateTimes(str);
			
			//查询每个用户所拥有的角色
			YzlUserRoleExample example2=new YzlUserRoleExample();
			com.yzl.pojo.YzlUserRoleExample.Criteria criteria2 = example2.createCriteria();
			criteria2.andUserIdEqualTo(Integer.valueOf(String.valueOf(yzlUser.getId())));
			
			List<YzlUserRole> list2 = userRoleMapper.selectByExample(example2);
			
			//存放角色id的list
			List<Integer> roleIdList=new ArrayList<>();
			
			if(list2.size() > 0 && list2 != null) {
				//遍历放到角色集合中在设置给用户
				for (int x=0;x<list2.size();x++) {
//					roleIdList.add(list2.get(i).getRoleId());
					YzlUserRole userRoleKey = list2.get(x);
					Integer roleId = userRoleKey.getRoleId();
					roleIdList.add(roleId);
				}
				yzlUser.setRoleIdList(roleIdList);
			}
		}
		PageInfo<YzlUser> pageInfo=new PageInfo<>(list);
		
		EasyUIResult result=new EasyUIResult();
		result.setRows(pageInfo.getList());
		result.setTotal(pageInfo.getTotal());
		
		return result;
	}

	/**
	 * 根据用户名查询
	 */
	@Override
	public YzlUser findByUsername(String username) {
		
		YzlUserExample example=new YzlUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		
		List<YzlUser> list = userMapper.selectByExample(example);
		if(list.size()!=0) {
			return list.get(0);
		}
		return null;
	}

	//批量删除
	@Override
	public YzlResult deleteUser(String[] ids) {
		
		int isok=0;
		
		for(int i=0;i<ids.length;i++) {
			
			//根据id查询用户
			YzlUser user = userMapper.selectByPrimaryKey(Long.valueOf(ids[i]));
			
			//查询跟这个用户有关的角色
			YzlUserRoleExample example = new YzlUserRoleExample();
			com.yzl.pojo.YzlUserRoleExample.Criteria criteria=example.createCriteria();
			criteria.andUserIdEqualTo(Integer.valueOf(String.valueOf(user.getId())));
			
			List<YzlUserRole> list = userRoleMapper.selectByExample(example);
			if (list.size() > 0 && list != null) {
				for (YzlUserRole userRoleKey : list) {
					userRoleMapper.deleteByPrimaryKey(userRoleKey.getId());
//					userRoleMapper.deleteByPrimaryKey(userRoleKey);
				}
			}
			isok = userMapper.deleteByPrimaryKey(user.getId());
		}
		if (isok>0) {
			return YzlResult.ok(200);
		}
		return YzlResult.ok(400);
	}

	//修改用户
	@Override
	public YzlResult updateUser(YzlUser user) {
		long id = user.getId();
		YzlUser yzlUser = userMapper.selectByPrimaryKey(id);
		if (!yzlUser.getPassword().equals(user.getPassword()) && !"".equals(user.getPassword())) {
			String password = user.getPassword();
			user.setPassword(MD5Utils.md5(password));
		}else {
			user.setPassword(yzlUser.getPassword());
		}
		
		//修改用户信息
		userMapper.updateByPrimaryKey(user);
		
		//通过用户的id查询用户与角色表
		YzlUserRoleExample example=new YzlUserRoleExample();
		com.yzl.pojo.YzlUserRoleExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(Integer.valueOf(String.valueOf(user.getId())));
		
		List<YzlUserRole> list = userRoleMapper.selectByExample(example);
		//通过id 删除
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				userRoleMapper.deleteByPrimaryKey(list.get(i).getId());
			}
		}
		
		//获取用户拥有的id
		List<Integer> roleIdList = user.getRoleIdList();
		if(roleIdList != null && roleIdList.size() > 0) {
			for (int i = 0; i < roleIdList.size(); i++) {
				YzlUserRole record=new YzlUserRole();
				record.setRoleId(roleIdList.get(i));
				record.setUserId(Integer.valueOf(String.valueOf(user.getId())));
				int insert = userRoleMapper.insert(record);
			}
		}
		
		
		return YzlResult.ok(400);
	}
}
