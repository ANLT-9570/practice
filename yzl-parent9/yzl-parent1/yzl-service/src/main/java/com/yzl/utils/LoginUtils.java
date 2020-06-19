package com.yzl.utils;

import java.util.List;

import com.yzl.mapper.YzlMenuMapper;
import com.yzl.pojo.YzlUser;
import com.yzl.utils.LoginUserUtils;

public class LoginUtils {
	
	
	public static List<String> getLoginUserAuthority(YzlMenuMapper menuMapper){
		YzlUser loginUser = LoginUserUtils.getLoginUser();
		String userId = loginUser.getId().toString();
		return menuMapper.queryPermsByUserId(Integer.valueOf(userId));
	}

}
