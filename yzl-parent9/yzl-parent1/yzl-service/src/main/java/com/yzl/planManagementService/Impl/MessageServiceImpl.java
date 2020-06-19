package com.yzl.planManagementService.Impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzl.mapper.YzlMenuMapper;
import com.yzl.mapper.YzlMessageMapper;
import com.yzl.mapper.YzlUserRoleMapper;
import com.yzl.planManagementService.MessageService;
import com.yzl.pojo.YzlUser;
import com.yzl.utils.LoginUserUtils;
import com.yzl.utils.YzlResult;


@Service

public class MessageServiceImpl implements MessageService{

	@Autowired
	private YzlMessageMapper messageMapper;
	@Autowired
	private YzlUserRoleMapper userRoleMapper;
	@Autowired
	private YzlMenuMapper menuMapper;
	
	
	
	
	


	/**
	 * 设置已读状态
	 */
	@Override
	public YzlResult updateStatuByUserId() {
		//获取当前登录用户
		YzlUser loginUser = LoginUserUtils.getLoginUser();
		System.err.println("user="+loginUser);
		//根据用户Id查询所有权限
		List<String> authoritys = menuMapper.queryPermsByUserId(Integer.valueOf(loginUser.getId().toString()));
		int result = messageMapper.updateStatuByUserIdAndCountCode(loginUser.getKeywork(), authoritys);
		System.err.println(loginUser.getKeywork());
		if(!(result>0)){
			return YzlResult.build(500, "消息更新失败");
		}
		return YzlResult.ok();
	}



	/***
	 * 根据用户id 统计记录数
	 */
	@Override
	public int getRecordsByUserId(HttpServletRequest request) {
		//获取当前登录用户
//		YzlUser loginUser = LoginUserUtils.getLoginUser();
//		getLoginUser
		YzlUser loginUser = LoginUserUtils.getLoginSession(request);
		 String keywork = loginUser.getKeywork();
		//根据用户Id查询所有权限
		List<String> PermsList = menuMapper.queryPermsByUserId(Integer.valueOf(String.valueOf(loginUser.getId())));
		int messageCount = 0;
		try {
			messageCount = messageMapper.countMessageNumber(PermsList,keywork);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("updateDataNumber", messageCount);
		return messageCount;
	}

}
