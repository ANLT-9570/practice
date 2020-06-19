package com.yzl.utils.enums;

public enum UserEnum {
	
	//当前已登录用户
	LOGIN_USER("loginUser"),
	
	;
        private String status;
        
        private UserEnum(String status) {
        	this.status=status;
        }

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
        
}
