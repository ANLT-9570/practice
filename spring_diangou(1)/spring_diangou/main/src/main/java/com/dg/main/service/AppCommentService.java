package com.dg.main.service;

import java.util.List;

import com.dg.main.Entity.AppComment;
import com.dg.main.Entity.AppReply;
import com.dg.main.Entity.users.MobileUser;


public interface AppCommentService {
	
	public List<AppComment> list (Long SpecificationsId);
	
	public int add(AppComment appComment);
	public MobileUser selectMobileUse(Long mobileUseId);
	public int addReply(AppReply appReply);

}
