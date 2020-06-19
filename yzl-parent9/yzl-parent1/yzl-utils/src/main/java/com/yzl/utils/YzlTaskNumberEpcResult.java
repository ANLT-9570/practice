package com.yzl.utils;

import java.util.List;

import com.yzl.pojo.YzlEpc;

public class YzlTaskNumberEpcResult {
	
	//工程
	YzlEpc epc;
	//当前工程任务个数
	Integer taskNumber;
	
	public YzlTaskNumberEpcResult(){};
	
	public YzlTaskNumberEpcResult(YzlEpc epc, Integer taskNumber) {
		super();
		this.epc = epc;
		this.taskNumber = taskNumber;
	}
	
	public YzlEpc getEpc() {
		return epc;
	}

	public void setEpc(YzlEpc epc) {
		this.epc = epc;
	}
	public Integer getTaskNumber() {
		return taskNumber;
	}
	public void setTaskNumber(Integer taskNumber) {
		this.taskNumber = taskNumber;
	}
	
	
}
