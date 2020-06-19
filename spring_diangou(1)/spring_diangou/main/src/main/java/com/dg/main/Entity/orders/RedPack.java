package com.dg.main.Entity.orders;

import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.NavigableMap;

//红包
@Entity
@Table(name = "red_pack")
public class RedPack   implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "platform_money")
    private Long platformMoney;//平台的钱
    @Column(name = "shop_id")
    private Long shopId;
    @Column(name = "code")
    private String code;
    @Column(name = "version")
    private Long version;
    @Column(name = "modified")
    private Timestamp modified=new Timestamp(new Date().getTime());
    @Column(name = "create_time")
    private Timestamp createTime=new Timestamp(new Date().getTime());
    @Column(name = "send_time")
    private Timestamp sendTime=null;

    @Column(name = "is_taked")
    private Integer isTaked=2;
    @Column(name = "take_number")
    private Integer takeNumber=0;
    @Column(name = "scheduler_time")
    private Timestamp schedulerTime=null;
    @Column(name = "user_taked")
    private Integer userTaked=0;
    @Column(name = "generated_numbers")
    private String generatedNumbers;
    @Column(name = "left_money")
    private Long leftMoney;
    @Column(name = "shop_name")
    private String shopName;

    public Long getLeftMoney() {
        return leftMoney;
    }

    public void setLeftMoney(Long leftMoney) {
        this.leftMoney = leftMoney;
    }

    public String getGeneratedNumbers() {
        return generatedNumbers;
    }

    public void setGeneratedNumbers(String generatedNumbers) {
        this.generatedNumbers = generatedNumbers;
    }

    public Timestamp getSchedulerTime() {
        return schedulerTime;
    }

    public void setSchedulerTime(Timestamp schedulerTime) {
        this.schedulerTime = schedulerTime;
    }

    public Integer getUserTaked() {
        return userTaked;
    }

    public void setUserTaked(Integer userTaked) {
        this.userTaked = userTaked;
    }

    public Integer getTakeNumber() {
        return takeNumber;
    }

    public void setTakeNumber(Integer takeNumber) {
        this.takeNumber = takeNumber;
    }

    public Integer getIsTaked() {
        return isTaked;
    }

    public void setIsTaked(Integer isTaked) {
        this.isTaked = isTaked;
    }

    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Timestamp getModified() {
        return modified;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPlatformMoney() {
        return platformMoney;
    }

    public void setPlatformMoney(Long platformMoney) {
        this.platformMoney = platformMoney;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public RedPack() {
    }

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	@Override
	public String toString() {
		return "RedPack [id=" + id + ", userId=" + userId + ", platformMoney=" + platformMoney + ", shopId=" + shopId
				+ ", code=" + code + ", version=" + version + ", modified=" + modified + ", createTime=" + createTime
				+ "]";
	}
    
}
