package com.yzl.utils.enums;

public enum MessageStatus {
	
	MESSAGE_STATU_NOT_READ(0),
	MESSAGE_STATU_READ(1);
	
	;
	private Integer stat;

	public Integer getStat() {
		return stat;
	}

	public void setStat(Integer stat) {
		this.stat = stat;
	}

	private MessageStatus(Integer stat) {
		this.stat = stat;
	}


	
}
