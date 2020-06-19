package com.yzl.pojo;

import java.util.List;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2018-12-22
 */
public class YzlEpc {
	
	private List<YzlTask> list;
	
    public List<YzlTask> getList() {
		return list;
	}

	public void setList(List<YzlTask> list) {
		this.list = list;
	}
	private String field;
	
	public String getField() {
		return field;
	}
	
	public void setField(String field) {
		this.field = field;
	}
    /**
     * 工程编号
     */
    private Integer ecode;

    /**
     * 工程名称
     */
    private String ename;

    /**
     * 标识
     */
    private String mark;

    public Integer getEcode() {
        return ecode;
    }

    public void setEcode(Integer ecode) {
        this.ecode = ecode;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename == null ? null : ename.trim();
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }
}