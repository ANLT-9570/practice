package com.dg.main.service;

import java.util.List;

import com.dg.main.Entity.Follow;

import com.dg.main.util.slzcResult;
import com.dg.main.base.BaseMapper;
import com.dg.main.util.Result;
import com.dg.main.vo.FollowVo;

public interface FollowServer extends BaseMapper<Follow>{

	public slzcResult selectAll(int offset, int limit,String search);
	//根据用户id查询拥有的关注
	List<FollowVo> findByMyFollow(Long uid);
	public int clickFollow(Long uid, Long shopId);
	public Follow findByClickFollow(Long uid, Long shopId);
	public int cancleFollow(Long uid, Long shopId,Long status);
	public Result UpdatesFollow(String [] follow);

}
