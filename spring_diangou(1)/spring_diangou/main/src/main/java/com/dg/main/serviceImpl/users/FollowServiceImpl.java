package com.dg.main.serviceImpl.users;

import java.util.List;

import com.dg.main.vo.FollowVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dg.main.Entity.Follow;

import com.dg.main.util.slzcResult;
import com.dg.main.dao.FollowMapper;
import com.dg.main.service.FollowServer;
import com.dg.main.util.Result;

@Service
public class FollowServiceImpl implements FollowServer{

	@Autowired
	private FollowMapper followMapper;
	
	@Override
	public Follow selectOne(Follow t) {
		
		return followMapper.selectOne(t);
	}


	@Override
	public int delete(Follow t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Follow t) {
		
		return followMapper.update(t);
	}

	@Override
	public int save(Follow t) {
		
		return followMapper.save(t);
	}


	@Override
	public List<Follow> selectAll(Follow t) {
		// TODO Auto-generated method stub
		return null;
	}


	//分页
	@Override
	public slzcResult selectAll(int offset,int limit,String search) {
		
		slzcResult result = new slzcResult();
		
		int count = followMapper.selectCount(search);
		
		List<Follow> administrator = followMapper.selectPageAll( offset,limit,search);
		result.setRows(administrator);
		result.setTotal(count);
		
		return result;
	}

	@Override
	public List<Follow> selectPageAll( int offset,int limit) {
		
		return null;
	}

	@Override
	public int deleteAllId(String [] t) {
		
		return followMapper.deleteAllId(t);
	}


	@Override
	public int selectCount() {
		// TODO Auto-generated method stub
		return 0;
	}


	//根据用户id查询
	@Override
	public List<FollowVo> findByMyFollow(Long uid) {
		List<FollowVo> list = followMapper.findByMyFollow(uid);
//		for (FollowVo followVo : list) {
//			String image = followVo.getImage();
//			String[] split = image.split("，");
//			if (split.length > 0) {
//				String string = split[0];
//				followVo.setImage(string);
//			}
//			
//			System.out.println(image);
//		}
		return list;
	}


	@Override
	public slzcResult selectAll(int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int clickFollow(Long uid, Long shopId) {
		int is = followMapper.clickFollow(uid,shopId);
		return is;
	}


	@Override
	public Follow findByClickFollow(Long uid, Long shopId) {
		Follow follow = followMapper.findByClickFollow(uid,shopId);
		return follow;
	}


	@Override
	public int cancleFollow(Long uid, Long shopId,Long status) {
		return followMapper.cancleFollow(uid,shopId,status);
	}


	@Override
	public Result UpdatesFollow(String [] follow) {
		int is = followMapper.UpdatesFollow(follow);
		if(is > 0){
			return Result.successResult();
		}else{
			return Result.failureResult("400", "错错了？");
		}
	}

}
