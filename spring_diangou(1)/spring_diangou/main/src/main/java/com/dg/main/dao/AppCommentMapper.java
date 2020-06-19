package com.dg.main.dao;

import java.util.List;

import com.dg.main.Entity.AppComment;
import com.dg.main.Entity.AppReply;

import com.dg.main.Entity.users.MobileUser;
import org.springframework.stereotype.Repository;

@Repository
public interface AppCommentMapper {
	
	public List<AppComment> list (Long SpecificationsId);
	
	public int add(AppComment appComment);
	
	public MobileUser selectMobileUse(Long mobileUseId);
	public int addReply(AppReply appReply);
	
}
