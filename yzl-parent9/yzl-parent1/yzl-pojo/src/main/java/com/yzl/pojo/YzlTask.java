package com.yzl.pojo;

import java.util.List;
import java.math.BigDecimal;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2018-12-22
 */
public class YzlTask {
	

	
	private List<YzlEpc> list;

 	BigDecimal cc;
	
	
    public List<YzlEpc> getList() {
		return list;
	}

	public void setList(List<YzlEpc> list) {
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
     * 任务编号id
     */
    private Integer tcode;

    /**
     * 任务名称
     */
    private String tname;

    /**
     * 标识
     */
    private String mark;

    //工程
    private YzlEpc epc;
    
    
    public YzlEpc getEpc() {
		return epc;
	}

	public void setEpc(YzlEpc epc) {
		this.epc = epc;
	}

	public Integer getTcode() {
        return tcode;
    }

    public void setTcode(Integer tcode) {
        this.tcode = tcode;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname == null ? null : tname.trim();
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }
}