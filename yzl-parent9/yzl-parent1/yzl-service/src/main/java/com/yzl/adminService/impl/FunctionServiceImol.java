package com.yzl.adminService.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzl.adminService.FunctionService;
import com.yzl.mapper.YzlMenuMapper;
import com.yzl.pojo.YzlMenu;
import com.yzl.pojo.YzlMenuExample;
import com.yzl.pojo.YzlUser;
import com.yzl.pojo.YzlMenuExample.Criteria;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.vo.TreeVO;


@Service
@Transactional
public class FunctionServiceImol implements FunctionService {

	@Autowired
	YzlMenuMapper menuMapper;
	
	/**
	 * 递归查询所有菜单
	 */
	@Override
	public List<TreeVO> getMenu() {
		//查询所有菜单，已JSON格式返回
		List<TreeVO> list = menuMapper.findAllRecursion();
		return list;
	}

	/***
	 * 添加
	 */
	@Override
	public boolean save(YzlMenu menu) {
		boolean flag=true;
		try {
			menuMapper.insert(menu);
		} catch (Exception e) {
			e.printStackTrace();
			flag=false;
		}
		return flag;
	}

	//分页查询
	@Override
	public EasyUIResult getFunctions(int page, int rows) {
		//设置查询条件
		PageHelper.startPage(page, rows);
		YzlMenuExample example = new YzlMenuExample();
		Criteria criteria = example.createCriteria();
		List<YzlMenu> list = menuMapper.selectByExample(example);
		
		PageInfo<YzlMenu> pageInfo = new PageInfo<>(list);
		//封装查询结果进pojo
		EasyUIResult result = new EasyUIResult();
		//封装总记录数
		result.setTotal((int)pageInfo.getTotal());
		//封装数据
		result.setRows(pageInfo.getList());
		return result;
	}
	
	//批量删除
	@Override
	public void delete(String ids) {
		String[] idList = ids.split(",");
		for (String id : idList) {
			this.RecurveDelete(Long.valueOf(id));
		}
	}

	
	//递归删除
	public void RecurveDelete(long id){
		//查询该权限是否是根节点
		YzlMenu menu = menuMapper.selectByPrimaryKey(id);
		//根据id查询是否还有子节点
		YzlMenuExample example = new YzlMenuExample();
		Criteria criteria = example.createCriteria();
		criteria.andPidEqualTo(menu.getMenuId().toString());
		List<YzlMenu> list = menuMapper.selectByExample(example);
		//如果为空就是没有跟子节点
		if(list==null || list.size()==0){
			//执行删除
			menuMapper.deleteByPrimaryKey(menu.getMenuId());
		}else{
			//如果是父节点，则继续查询
			for (YzlMenu yzlMenu : list) {
				this.RecurveDelete(yzlMenu.getMenuId());
			}
			//退出循环 删除父节点
			menuMapper.deleteByPrimaryKey(id);
		}
	}

	@Override
	public void update(YzlMenu menu) {
		menuMapper.updateByPrimaryKey(menu);
	}

	@Override
	public List<YzlMenu> findAllMenu() {
		YzlMenuExample example=new YzlMenuExample();
		return menuMapper.selectByExample(example);
	}

	@Override
	public List<YzlMenu> findFunctionByRoleId(Long valueOf) {
		
		return menuMapper.findMenuByRoleId(valueOf);
	}

	public List<YzlMenu> findAll(){
		
		//查询pid为null的就是主菜单
		List<YzlMenu> list = menuMapper.findAll();
		if (list != null && list.size() > 0) {
			for (YzlMenu yzlMenu : list) {
				dg(yzlMenu);
			}
		}
		return list;
	}
	
	//递归查询
	private void dg(YzlMenu yzlMenu) {
		List<YzlMenu> chile = menuMapper.findByPid(yzlMenu.getMenuId());
		for (YzlMenu yzlMenu2 : chile) {
			//注释递归
			dg(yzlMenu2);
		}
		yzlMenu.setChildren(chile);
	}
	
	@Override
	public List<YzlMenu> getMenus() {
		
		Session session = SecurityUtils.getSubject().getSession();
		YzlUser user  = (YzlUser) session.getAttribute("user");
		
		Integer id = Integer.valueOf(String.valueOf(user.getId()));
		
		if (id.equals(35)) {
			//获取所以权限
			
			return findAlls();
		}
		//根据用户获取对应的权限
//		List<YzlMenu> list = menuMapper.selectByUserId(id);
		
		//装主节点的集合
		List<YzlMenu> menus2 = menuMapper.selectByUserIdHost(id);
		
		//装子节点的集合
		List<YzlMenu> menus = menuMapper.selectByUserIdSon(id);
				
/*		if (list.size()>0) {
			for (YzlMenu yzlMenu : list) {
				//如果pid为空说明是主节点
				String pid = yzlMenu.getPid();
				//System.out.println(pid);
				if("".equals(yzlMenu.getPid()) || yzlMenu.getPid() == null) {
					menus2.add(yzlMenu);
				}else {
					menus.add(yzlMenu);
				}
			}
		}*/
		
		if (menus2.size()>0) {
			for (YzlMenu yzlMenu : menus2) {//遍历主节点
				if (menus.size()>0) {
					List<YzlMenu> mList=new ArrayList<>();
					for (YzlMenu yzlMenus : menus) {//遍历子节点
						Long menuId = yzlMenu.getMenuId();//主节点id
						String pid = yzlMenus.getPid();//子节点pid
						if (String.valueOf(menuId).equals(pid)) {
							mList.add(yzlMenus);
						}
					}
					yzlMenu.setChildren(mList);
				}
			}
		}
		for(int i=0;i<menus2.size();i++) {
			
			YzlMenu yzlMenu = menus2.get(i);
			int count = 0;
			if ("营造林辅助管理".equals(yzlMenu.getName())) {
				List<YzlMenu> list = yzlMenu.getChildren();
				for (YzlMenu yzlMenu2 : list) {
					String generatemenu = yzlMenu2.getGeneratemenu();
					if ("1".equals(generatemenu)) {
						count++;
						break;
					}
				}
				if (count==0) {
					menus2.remove(i);
				}
			}
			
		}
		
		return menus2;
	}
	
	public List<YzlMenu> findAlls(){
		//查询pid为null的就是主菜单
		List<YzlMenu> list = menuMapper.findAll();
		if (list != null && list.size() > 0) {
			for (YzlMenu yzlMenu : list) {
				List<YzlMenu> chile = menuMapper.findByPid(yzlMenu.getMenuId());
				yzlMenu.setChildren(chile);
			}
		}
		return list;
	}
	
}
