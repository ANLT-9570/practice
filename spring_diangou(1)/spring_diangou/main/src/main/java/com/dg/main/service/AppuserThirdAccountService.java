package com.dg.main.service;


import com.dg.main.Entity.users.UserThirdAccount;

public interface AppuserThirdAccountService {
	
	public int userThirdAccountSelect(UserThirdAccount userThirdAccount);
	
	public  int userThirdAccountAdd(UserThirdAccount userThirdAccount);
	public int userThirdAccountDelete(UserThirdAccount userThirdAccount);
	public UserThirdAccount userThirdAccountSelects(UserThirdAccount userThirdAccount);
	
	
}
