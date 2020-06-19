package com.dg.main.dao;


import java.util.List;

import com.dg.main.vo.FollowVo;
import org.apache.ibatis.annotations.Param;

import com.dg.main.Entity.Follow;

import com.dg.main.base.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowMapper extends BaseMapper<Follow>{

	List<Follow> selectPageAll(@Param("offset") int offset,@Param("limit")int limit,@Param("search")String search);
	
	int deleteAllId(@Param("t")String[] t) ;
	
	int selectCount(@Param("search")String search);
	
	 int update(@Param("t")Follow t) ;

	List<FollowVo> findByMyFollow(@Param("uid")Long uid);

	int clickFollow(Long uid, Long shopId);

	Follow findByClickFollow(Long uid, Long shopId);

	int cancleFollow(Long uid, Long shopId,Long status);

	int UpdatesFollow(String [] follow);
}
