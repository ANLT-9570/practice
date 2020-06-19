package com.dg.main.dao;


import java.util.List;


import com.dg.main.dao.orders.BaseDao;
import com.dg.main.vo.MyCollectionVo;
import org.apache.ibatis.annotations.Param;


import com.dg.main.Entity.MyCollection;
import com.dg.main.base.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionMapper extends BaseMapper<MyCollection>, BaseDao<MyCollection> {

	List<MyCollection> selectPageAll(@Param("offset") int offset,@Param("limit")int limit,@Param("search")String search);
	
	int deleteAllId(@Param("t")String[] t) ;
	
	 int selectCount(@Param("search")String search) ;
	
	 int update(@Param("t")MyCollection t) ;

	 //我的收藏
	List<MyCollectionVo> findUserCollectionGoods(@Param("uid")Integer uid, @Param("role") Integer role, @Param("status")Integer status, @Param("offset")Integer offset, @Param("limit")Integer limit);

	MyCollection IsOrNoCollect(Long uid, Long cid,Long shopId);

	int CancelCollect(Long uid, Long cid,int status,Long shopId);

	int addCollect(Long uid, Long cid,Long shopId);
}
