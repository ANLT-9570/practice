package com.dg.main.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dg.main.Entity.Comment;
import com.dg.main.base.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentMapper extends BaseMapper<Comment>{

	List<Comment> selectPageAll(@Param("offset") int offset,@Param("limit")int limit,@Param("search") String search);
	
	int deleteAllId(@Param("t")String[] t) ;
	
	 int selectCount(@Param("search") String search) ;
	
	 int update(@Param("t")Comment t) ;

	List<Comment> findBySpecificationsId(Long specificationsId);
}
