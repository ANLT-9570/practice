package com.dg.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dg.main.Entity.Menu;
import com.dg.main.Entity.Tree;
import com.dg.main.base.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuMapper extends BaseMapper<Menu>{

	//查询所以菜单
	List<Menu> findAllMenu(@Param("offset")int offset, @Param("limit")int limit,@Param("search") String search);

	//统计
	int selectCount(@Param("search")String search);

	//添加菜单
	int addMenu(Menu menu);

	int del(@Param("mids")String[] mids);

	List<Tree> selectAllMain(@Param("uid")Integer uid,@Param("number")String number);

	int addMenuRole(@Param("mids")String [] mids, @Param("rid")String rid);

	int delByRid(@Param("rid")String rid);

	int updateMenu(Menu menu);
	
	List<Tree> selectBySysMenu(@Param("number")String number);
	//删除菜单与角色的关联
	void delMenuRole(@Param("mids")String[] mids);

}
