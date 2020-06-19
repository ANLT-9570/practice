package com.dg.main.service;

import java.util.List;

import org.springframework.ui.ModelMap;

import com.dg.main.Entity.Menu;
import com.dg.main.Entity.Tree;
import com.dg.main.util.slzcResult;
import com.dg.main.base.BaseMapper;

public interface MenuService extends BaseMapper<Menu> {

	slzcResult findAllMenu(int offset, int limit,String search);

	ModelMap addMenu(Menu menu);

	ModelMap del(String[] mids);

	List<Tree> loadTree();

	ModelMap addMenuRole(String[] mids, String rid);

	ModelMap updateMenu(Menu menu);

}
