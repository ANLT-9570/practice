package com.dg.main.service;

import java.util.List;


import com.dg.main.Entity.MyCollection;
import com.dg.main.util.slzcResult;
import com.dg.main.base.BaseMapper;
import com.dg.main.vo.MyCollectionVo;

public interface CollectionServer extends BaseMapper<MyCollection>{

	 slzcResult selectAll(int offset, int limit,String search) ;
	
	//我的收藏
	List<MyCollectionVo> findUserCollectionGoods(Integer uid, Integer valueOf, Integer status, Integer offset, Integer limit);

	MyCollection IsOrNoCollect(Long uid, Long cid,Long shopId);

	int CancelCollect(Long uid, Long cid,int status,Long shopId);

	int addCollect(Long uid, Long cid,Long shopId);

}
