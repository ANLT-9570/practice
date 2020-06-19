package com.dg.main.dto.orders;

import java.sql.Timestamp;
import java.util.Date;

public class UserDepositPlatformMoneyDao {
    private Integer userId;
    private Timestamp createTime=new Timestamp(new Date().getTime());
    private Long platformMoney;
    private Integer isSuccess=2;
    private Integer direction=1; //支付方式
    private String depositStreamCode;
    private String  thirdCode;
    private String thirdNumber;
    private Long leftMoney=0l;
    private Long money;
    private String name;
    private String phone;
    private String role;
    private String realName;
    private String shopName;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getPlatformMoney() {
        return platformMoney;
    }

    public void setPlatformMoney(Long platformMoney) {
        this.platformMoney = platformMoney;
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

    public String getDepositStreamCode() {
        return depositStreamCode;
    }

    public void setDepositStreamCode(String depositStreamCode) {
        this.depositStreamCode = depositStreamCode;
    }

    public String getThirdCode() {
        return thirdCode;
    }

    public void setThirdCode(String thirdCode) {
        this.thirdCode = thirdCode;
    }

    public String getThirdNumber() {
        return thirdNumber;
    }

    public void setThirdNumber(String thirdNumber) {
        this.thirdNumber = thirdNumber;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public UserDepositPlatformMoneyDao() {
    }
}
