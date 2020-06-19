package com.dg.main.dto.orders;

import java.sql.Timestamp;

public class UserWithdrawPlatformMoneyDto {
	  private String withdrawStreamCode;

	    private Long platformMoney;
	    private Long currentRate;

	    private Integer isSuccess=2;

	    private Integer direction=1;
	    private Long leftMoney=0l;
	    private Long money;
	    private String ZhifubaoPayeeType="ALIPAY_LOGONID";
	    private String ZhifubaoPayeeAccount="";
	    private String ZhifubaoPayerShowName="";
	    private String ZhifubaoPayeeRealName="";
	    private String WeixinOpenId="";
	    private String WeixinRealName="";
	    private Integer userId;
	    private Long id;
	private Timestamp createTime;

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getWithdrawStreamCode() {
	        return withdrawStreamCode;
	    }

	    public void setWithdrawStreamCode(String withdrawStreamCode) {
	        this.withdrawStreamCode = withdrawStreamCode;
	    }

	    public Long getPlatformMoney() {
	        return platformMoney;
	    }

	    public void setPlatformMoney(Long platformMoney) {
	        this.platformMoney = platformMoney;
	    }

	    public Long getCurrentRate() {
	        return currentRate;
	    }

	    public void setCurrentRate(Long currentRate) {
	        this.currentRate = currentRate;
	    }

	    public Integer getIsSuccess() {
	        return isSuccess;
	    }

	    public void setIsSuccess(Integer isSuccess) {
	        this.isSuccess = isSuccess;
	    }

	    public Integer getDirection() {
	        return direction;
	    }

	    public void setDirection(Integer direction) {
	        this.direction = direction;
	    }

	    public Long getLeftMoney() {
	        return leftMoney;
	    }

	    public void setLeftMoney(Long leftMoney) {
	        this.leftMoney = leftMoney;
	    }

	    public Long getMoney() {
	        return money;
	    }

	    public void setMoney(Long money) {
	        this.money = money;
	    }

	    public String getZhifubaoPayeeType() {
	        return ZhifubaoPayeeType;
	    }

	    public void setZhifubaoPayeeType(String zhifubaoPayeeType) {
	        ZhifubaoPayeeType = zhifubaoPayeeType;
	    }

	    public String getZhifubaoPayeeAccount() {
	        return ZhifubaoPayeeAccount;
	    }

	    public void setZhifubaoPayeeAccount(String zhifubaoPayeeAccount) {
	        ZhifubaoPayeeAccount = zhifubaoPayeeAccount;
	    }

	    public String getZhifubaoPayerShowName() {
	        return ZhifubaoPayerShowName;
	    }

	    public void setZhifubaoPayerShowName(String zhifubaoPayerShowName) {
	        ZhifubaoPayerShowName = zhifubaoPayerShowName;
	    }

	    public String getZhifubaoPayeeRealName() {
	        return ZhifubaoPayeeRealName;
	    }

	    public void setZhifubaoPayeeRealName(String zhifubaoPayeeRealName) {
	        ZhifubaoPayeeRealName = zhifubaoPayeeRealName;
	    }

	    public String getWeixinOpenId() {
	        return WeixinOpenId;
	    }

	    public void setWeixinOpenId(String weixinOpenId) {
	        WeixinOpenId = weixinOpenId;
	    }

	    public String getWeixinRealName() {
	        return WeixinRealName;
	    }

	    public void setWeixinRealName(String weixinRealName) {
	        WeixinRealName = weixinRealName;
	    }

	    public Integer getUserId() {
	        return userId;
	    }

	    public void setUserId(Integer userId) {
	        this.userId = userId;
	    }

	    public UserWithdrawPlatformMoneyDto() {
	    }
}
