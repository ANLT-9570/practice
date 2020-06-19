package com.dg.main.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dg.main.Entity.Comment;
import com.dg.main.util.slzcResult;
import com.dg.main.dao.CommentMapper;
import com.dg.main.service.CommentServer;

@Service
public class CommentServiceImpl implements CommentServer{

	@Autowired
	private CommentMapper feedbackMapper;
	

	@Override
	public int delete(Comment t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Comment t) {
		
		return feedbackMapper.update(t);
	}

	@Override
	public int save(Comment t) {
		
		return feedbackMapper.save(t);
	}


	@Override
	public List<Comment> selectAll(Comment t) {
		// TODO Auto-generated method stub
		return null;
	}


	//分页
	@Override
	public slzcResult selectAll(int offset,int limit,String search) {
		
		slzcResult result = new slzcResult();
		
		int count = feedbackMapper.selectCount(search);
		
		List<Comment> administrator = feedbackMapper.selectPageAll( offset,limit,search);
		result.setRows(administrator);
		result.setTotal(count);
		
		return result;
	}

	@Override
	public List<Comment> selectPageAll( int offset,int limit) {
		
		return null;
	}

	@Override
	public int deleteAllId(String [] t) {
		
		return feedbackMapper.deleteAllId(t);
	}


	@Override
	public int selectCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Comment selectOne(Comment t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public slzcResult selectAll(int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> findBySpecificationsId(Long specificationsId) {
		
		return feedbackMapper.findBySpecificationsId( specificationsId);
	}

}
