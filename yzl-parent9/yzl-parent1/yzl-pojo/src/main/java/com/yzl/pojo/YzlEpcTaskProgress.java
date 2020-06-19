package com.yzl.pojo;

import java.util.Date;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2018-12-29
 */
public class YzlEpcTaskProgress {
	
	

	private YzlDistrict district;

	public YzlDistrict getDistrict() {
		return district;
	}

	public void setDistrict(YzlDistrict district) {
		this.district = district;
	}

	private Long id;

    /**
     * 任务进度
     */
    private Float taskprogress;

    /**
     * 任务计划时间
     */
    private Date createtime;

    /**
     * 工程标识编号
     */
    private String gclb;

    /**
     * 任务标识编号
     */
    private String zllb;

    /**
     * 市行政编码
     */
    private String citycode;

    /**
     * 县行政编号/数据访问权限
     */
    private String countycode;

    /**
     * 创建的用户
     */
    private String creator;

    /**
     * 修改的用户
     */
    private String modifier;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 退回任务的用户
     */
    private Integer sendback;

    /**
     * 造林实施年度
     */
    private String zynd;

    /**
     * 造林计划年度
     */
    private String jhnd;
    
    /***
     * 上传文件的相对路径
     */
    private String filesUrl;
    
    private String stat;
    
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

    public String getGclb() {
        return gclb;
    }

    public void setGclb(String gclb) {
        this.gclb = gclb == null ? null : gclb.trim();
    }

    public String getZllb() {
        return zllb;
    }

    public void setZllb(String zllb) {
        this.zllb = zllb == null ? null : zllb.trim();
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode == null ? null : citycode.trim();
    }

    public String getCountycode() {
        return countycode;
    }

    public void setCountycode(String countycode) {
        this.countycode = countycode == null ? null : countycode.trim();
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

    public Integer getSendback() {
        return sendback;
    }

    public void setSendback(Integer sendback) {
        this.sendback = sendback;
    }

    public String getZynd() {
        return zynd;
    }

    public void setZynd(String zynd) {
        this.zynd = zynd == null ? null : zynd.trim();
    }

    public String getJhnd() {
        return jhnd;
    }

    public void setJhnd(String jhnd) {
        this.jhnd = jhnd == null ? null : jhnd.trim();
    }

	public String getFilesUrl() {
		return filesUrl;
	}

	public void setFilesUrl(String filesUrl) {
		this.filesUrl = filesUrl;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	@Override
	public String toString() {
		return "YzlEpcTaskProgress [district=" + district + ", id=" + id + ", taskprogress=" + taskprogress
				+ ", createtime=" + createtime + ", gclb=" + gclb + ", zllb=" + zllb + ", citycode=" + citycode
				+ ", countycode=" + countycode + ", creator=" + creator + ", modifier=" + modifier + ", updatetime="
				+ updatetime + ", sendback=" + sendback + ", zynd=" + zynd + ", jhnd=" + jhnd + ", filesUrl=" + filesUrl
				+ "]";
	}

    
}