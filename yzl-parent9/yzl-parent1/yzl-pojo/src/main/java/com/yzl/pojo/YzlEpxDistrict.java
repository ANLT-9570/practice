package com.yzl.pojo;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2018-12-19
 */
public class YzlEpxDistrict {
    private Long id;

    /**
     * 地区编号id
     */
    private Long dcode;

    /**
     * 工程编号id
     */
    private Long ecode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDcode() {
        return dcode;
    }

    public void setDcode(Long dcode) {
        this.dcode = dcode;
    }

    public Long getEcode() {
        return ecode;
    }

    public void setEcode(Long ecode) {
        this.ecode = ecode;
    }
}