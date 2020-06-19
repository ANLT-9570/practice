package com.yzl.pojo;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2018-12-19
 */
public class YzlDistrict {
	private String name;
	
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String anumber;
	
	public String getAnumber() {
		return anumber;
	}

	public void setAnumber(String anumber) {
		this.anumber = anumber;
	}

	/**
     * 地区编号
     */
    private Integer dcode;

    /**
     * 市
     */
    private String city;

    /**
     * 县
     */
    private String county;

    /**
     * 上级编号
     */
    private Integer pcode;

    /**
     * 简码
     */
    private String shortcode;

    /**
     * 市级行政编号
     */
    private String citycode;

    /**
     * 镇
     */
    private String burg;

    /**
     * 村
     */
    private String village;

    /**
     * 每个市县的标志
     */
    private String mark;

    /**
     * 每个市的标志
     */
    private String flag;

    public Integer getDcode() {
        return dcode;
    }

    public void setDcode(Integer dcode) {
        this.dcode = dcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county == null ? null : county.trim();
    }

    public Integer getPcode() {
        return pcode;
    }

    public void setPcode(Integer pcode) {
        this.pcode = pcode;
    }

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode == null ? null : shortcode.trim();
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode == null ? null : citycode.trim();
    }

    public String getBurg() {
        return burg;
    }

    public void setBurg(String burg) {
        this.burg = burg == null ? null : burg.trim();
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village == null ? null : village.trim();
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }
}