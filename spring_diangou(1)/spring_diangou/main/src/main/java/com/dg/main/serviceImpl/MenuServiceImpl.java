package com.dg.main.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.dg.main.Entity.Menu;

import com.dg.main.Entity.Tree;
import com.dg.main.util.slzcResult;
import com.dg.main.dao.MenuMapper;
import com.dg.main.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;

	//@Autowired
	//private UserMapper userMapper;
	
	// 查询所有菜单
	@Override
	public slzcResult findAllMenu(int offset, int limit,String search) {

		long total = menuMapper.selectCount(search);// 总记录数
		List<Menu> menus = menuMapper.findAllMenu(offset, limit,search);

		slzcResult result = new slzcResult();
		result.setRows(menus);
		result.setTotal(total);

		return result;
	}

	// 添加菜单
	@Override
	public ModelMap addMenu(Menu menu) {
		ModelMap map = new ModelMap();
		int res = menuMapper.addMenu(menu);
		if (res > 0) {
			map.put("data",200);
			return map;
		}
		map.put("data",400);
		return map;
	}

	// 删除
	@Override
	public ModelMap del(String[] mids) {
		ModelMap map = new ModelMap();
		//删除菜单与角色的关联
		menuMapper.delMenuRole(mids);
		
		int res = menuMapper.del(mids);
		if (res != 0 && mids.length == res) {
			map.put("data",200);
			return map;
		}
		map.put("data",400);
		return map;
	}

	//加载菜单树
	@Override
	public List<Tree> loadTree() {

//		Subject subject = SecurityUtils.getSubject();
//		User user = (User) subject.getSession().getAttribute("user");
		
		// 系统主菜单
		List<Tree> mainTrees = menuMapper.selectBySysMenu("1");
//		List<Tree> userTrees = menuMapper.selectAllMain(user.getId(),NumCode.ONE.getCode());
		
		// 系统主菜单
		List<Tree> isNT = menuMapper.selectBySysMenu("0");
//		List<Tree> userisNT = menuMapper.selectAllMain(user.getId(),NumCode.ZERO.getCode());
		
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
	
	//菜单和角色关联

	@Override
	public ModelMap addMenuRole(String[] mids, String rid) {
		ModelMap map = new ModelMap();
		//删除在从新添加
		menuMapper.delByRid(rid);
		
		//添加
		int sum = 0;
		if (mids != null && mids.length > 0) {
			sum = menuMapper.addMenuRole(mids,rid);
		}
		if (mids == null || sum > 0) {
			map.put("data",200);
			return map;
		}
		map.put("data",400);
		return map;
	}

	
	@Override
	public ModelMap updateMenu(Menu menu) {
		ModelMap map = new ModelMap();
		//
		int rs = menuMapper.updateMenu(menu);
		if (rs > 0) {
			map.put("data",200);
			return map;
		}
		map.put("data",400);
		return map;
	}

	@Override
	public Menu selectOne(Menu t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Menu t) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int update(Menu t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int save(Menu t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Menu> selectAll(Menu t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Menu> selectPageAll(int limit, int offset) {
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
