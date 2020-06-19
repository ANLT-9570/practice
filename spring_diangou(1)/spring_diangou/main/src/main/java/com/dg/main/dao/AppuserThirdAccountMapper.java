package com.dg.main.dao;


import com.dg.main.Entity.users.UserThirdAccount;
import org.springframework.stereotype.Repository;

@Repository
public interface AppuserThirdAccountMapper {
	public int userThirdAccountSelect(UserThirdAccount userThirdAccount);
	
	public  int userThirdAccountAdd(UserThirdAccount userThirdAccount);
	
	public int userThirdAccountDelete(UserThirdAccount userThirdAccount);
	public UserThirdAccount  userThirdAccountSelects(UserThirdAccount userThirdAccount);

}
