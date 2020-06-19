package com.yzl.utils.exception;

import com.yzl.utils.enums.ResultEnum;

public class YzlException extends RuntimeException{
	
	private int code;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public YzlException(ResultEnum resultEnum) {
		super(resultEnum.getMessage());
		this.code = resultEnum.getCode();
	}

	public YzlException(int code, String msg) {
		super(msg);
		this.code = code;
	}
	public YzlException() {

	}
	
	
}
