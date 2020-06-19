package com.yzl.pojo;

import java.util.Date;

/**
 * 任务进度工作表
 * 
 * @author wcyong
 * 
 * @date 2018-12-19
 */
public class YzlTaskProgressSheet {
	
	private String createTimetoString;
	
	public String getCreateTimetoString() {
		return createTimetoString;
	}

	public void setCreateTimetoString(String createTimetoString) {
		this.createTimetoString = createTimetoString;
	}

	private YzlTask task;
	
	public YzlTask getTask() {
		return task;
	}

	public void setTask(YzlTask task) {
		this.task = task;
	}

	private YzlDistrict district;
	
	public YzlDistrict getDistrict() {
		return district;
	}

	public void setDistrict(YzlDistrict district) {
		this.district = district;
	}

	private String databasetime;
	
    public String getDatabasetime() {
		return databasetime;
	}

	public void setDatabasetime(String databasetime) {
		this.databasetime = databasetime;
	}

	private Long id;

    /**
     * 任务进度
     */
    private Float taskprogress;

    /**
     * 创建时间/任务计划时间
     */
    private Date createtime;

    /**
     * 工程编号id
     */
    private Integer epcode;

    /**
     * 任务编号id
     */
    private Integer tpcode;

    /**
     * 地区id
     */
    private Integer dpcode;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 根据市名+‘-’+县名拼接成
     */
    private String mark;

    /**
     * 做保留字段
     */
    private Integer sendback;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getTaskprogress() {
        return taskprogress;
    }

    public void setTaskprogress(Float taskprogress) {
        this.taskprogress = taskprogress;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getEpcode() {
        return epcode;
    }

    public void setEpcode(Integer epcode) {
        this.epcode = epcode;
    }

    public Integer getTpcode() {
        return tpcode;
    }

    public void setTpcode(Integer tpcode) {
        this.tpcode = tpcode;
    }

    public Integer getDpcode() {
        return dpcode;
    }

    public void setDpcode(Integer dpcode) {
        this.dpcode = dpcode;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }

    public Integer getSendback() {
        return sendback;
    }

    public void setSendback(Integer sendback) {
        this.sendback = sendback;
    }
}