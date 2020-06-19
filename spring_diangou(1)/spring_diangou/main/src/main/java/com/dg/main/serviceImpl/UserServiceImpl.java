package com.dg.main.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dg.main.dao.orders.UserBalanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.dg.main.Entity.Menu;

import com.dg.main.Entity.Tree;
import com.dg.main.Entity.User;
import com.dg.main.util.slzcResult;
import com.dg.main.dao.UserMapper;


@Service
public class UserServiceImpl{
	

	@Autowired
	private UserMapper userMapper;
	@Autowired
	UserBalanceMapper userBalanceMapper;


	public User selectOne(User t) {
		// TODO Auto-generated method stub
		return userMapper.selectOne(t);
	}


	public User selectAll(User t) {
		// TODO Auto-generated method stub
		return null;
	}


	public int delete(User t) {
		// TODO Auto-generated method stub
		return 0;
	}


	public int deleteAllId(User t) {
		// TODO Auto-generated method stub
		return 0;
	}


	public int update(User t) {
		int update = userMapper.update(t);
		return update;
	}


	public int save(User t) {
		// TODO Auto-generated method stub
		return 0;
	}

	//查询所有用户
		public slzcResult findAllUser(int limit,int offset,String search) {
			
			//PageHelper.startPage(offset,limit);
			long total = userMapper.selectCounty(search);//总记录数
			ArrayList<User> users= userMapper.selectAllUser(limit,offset,search);
			
			//PageInfo<user> info = new PageInfo<user>(users);
			
			slzcResult result = new slzcResult();
			result.setRows(users);
			result.setTotal(total);
			
			return result;
		}

		//添加用户
		public ModelMap addUser(User user) {
			ModelMap map = new ModelMap();
			User users = userMapper.findByUsername(user.getAccount());
			if(users != null) {
				map.put("data",400);
				return map;
			}
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
			String date = format.format(new Date());
			
			user.setCreatetime(date);
			int id=userMapper.addUser(user);
//			UserBalance userBalance=new UserBalance();
//			userBalance.setCreateTime(new Date());
//			userBalance.setModifyTime(new Date());
//			userBalance.setUserId(user.getId());
		//	userBalanceMapper.save(userBalance);
			map.put("data",200);
			//map.put("id",id);
			return map;
		}

		public List<Tree> findAllRole(String uid) {
			//Subject subject = SecurityUtils.getSubject();
			//User user = (User) subject.getSession().getAttribute("user");
			//用户的拥有的角色
			List<Tree> userRole = userMapper.selectByUserRole(Integer.valueOf(uid));
			//所有的
			List<Tree> roles = userMapper.findAllRole();
			for (Tree tree : roles) {
				for (Tree useTree : userRole) {
					
					String id = tree.getId();
					String useId = useTree.getId();
					if (id.equals(useId)) {
						tree.setChecked("true");
					}
				}
			}
			return roles;
		}

		public ModelMap addUserRole(String[] rids, String uid) {
			ModelMap map = new ModelMap();
			//根据用户id删除
			int sum = userMapper.delUserRole(uid);
			int ur = userMapper.addUserRole(rids,uid);
			System.out.println(sum+ur);
			if (ur > 0) {
				map.put("data",200);
				return map;
			}
			map.put("data",400);
			return map;
		}

		//查询用户拥有的权限
		public List<Menu> findPms(User user) {
			
			List<Menu> ChildrenMenus = new ArrayList<Menu>();
			List<Menu> MainMenus = new ArrayList<Menu>();
			
			if (user!=null) {
				//查询主菜单
				MainMenus = userMapper.findByMainMen(user.getId(),"1");
				
				//子节点
				ChildrenMenus = userMapper.findByMen(user.getId(),"0");
				
				if (MainMenus .size() > 0) {
					for (Menu mainMenu : MainMenus) {//遍历主节点
						
						if (ChildrenMenus.size() > 0) {//遍历子节点
							
							List<Menu> newMenus = new ArrayList<Menu>();
							
							for (Menu ChildrenMenu : ChildrenMenus) {
								
								String code = mainMenu.getCode();//主id
								String pcode = ChildrenMenu.getPcode();//子id
								
								if (code.equals(pcode)) {
									newMenus.add(ChildrenMenu);
								}
							}
							if (newMenus.size() > 0) {
								mainMenu.setChildrens(newMenus);
							}
						}
					}
				}
			}
			return MainMenus;
		}

		public ModelMap delUser(String [] uids) {
			ModelMap map = new ModelMap();
			//删除用户
			int rs = userMapper.delUser(uids);
			//删除用户与角色的关联
			for (String uid : uids) {
				userMapper.delUserRole(uid);
			}
			if (uids.length == rs) {
				map.put("data",200);
				return map;
			}
			map.put("data",400);
			return map;
		}

		


}
