package com.dg.main.serviceImpl;

import java.util.List;

import com.dg.main.Entity.users.MobileUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dg.main.Entity.AppComment;
import com.dg.main.Entity.AppReply;

import com.dg.main.dao.AppCommentMapper;
import com.dg.main.service.AppCommentService;


@Service
public class AppCommentServiceImpl implements AppCommentService{
	
	@Autowired
	private AppCommentMapper appCommentMapper;

	@Override
	public List<AppComment> list( Long SpecificationsId) {
		// TODO Auto-generated method stub
		return appCommentMapper.list(SpecificationsId);
	}

	@Override
	public int add(AppComment appComment) {
		// TODO Auto-generated method stub
		return appCommentMapper.add(appComment);
	}

	@Override
	public MobileUser selectMobileUse(Long mobileUseId) {
		// TODO Auto-generated method stub
		return appCommentMapper.selectMobileUse(mobileUseId);
	}

	@Override
	public int addReply(AppReply appReply) {
		// TODO Auto-generated method stub
		return appCommentMapper.addReply(appReply);
	}

}
