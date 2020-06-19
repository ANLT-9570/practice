package com.dg.main.service;

import java.util.List;

import com.dg.main.Entity.Comment;
import com.dg.main.util.slzcResult;
import com.dg.main.base.BaseMapper;

public interface CommentServer extends BaseMapper<Comment>{

	slzcResult selectAll(int offset, int limit,String search) ;

	List<Comment> findBySpecificationsId(Long specificationsId);
}
