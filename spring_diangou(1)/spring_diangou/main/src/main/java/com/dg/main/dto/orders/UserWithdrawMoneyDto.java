package com.dg.main.dto.orders;

import java.sql.Timestamp;
import java.util.Date;

public class UserWithdrawMoneyDto {
	private String ZhifubaoPayeeType="ALIPAY_LOGONID";
    private String ZhifubaoPayeeAccount="";
    private String ZhifubaoPayerShowName="";
    private String ZhifubaoPayeeRealName="";
    private String WeixinOpenId="";
    private String WeixinRealName="";
    private Integer userId;
    private Long id;
    private String withdrawStreamCode;
    private Long money;
    private Long leftMoney;
    private Timestamp createTime;
    private Integer isSuccess=2;

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    private Integer direction=1;

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

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Long getLeftMoney() {
        return leftMoney;
    }

    public void setLeftMoney(Long leftMoney) {
        this.leftMoney = leftMoney;
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

    public UserWithdrawMoneyDto() {
    }
}
