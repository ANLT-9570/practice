package com.dg.main.dto.orders;

import java.sql.Timestamp;
import java.util.Date;

public class UserStreamLogDto {
    private String shopName; //店名
    private String realityName;//真实姓名
    private String name;
    private String  phone;
    private Integer types;
    private String code;
    private String thirdCode;
    private Date createTime;
    private Long preMoney;
    private Long currentMoney;
    private Long prePlatformMoney;
    private Long currentPlatfomrMoney;
    private String role;
    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getPreMoney() {
        return preMoney;
    }

    public void setPreMoney(Long preMoney) {
        this.preMoney = preMoney;
    }

    public Long getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(Long currentMoney) {
        this.currentMoney = currentMoney;
    }

    public Long getPrePlatformMoney() {
        return prePlatformMoney;
    }

    public void setPrePlatformMoney(Long prePlatformMoney) {
        this.prePlatformMoney = prePlatformMoney;
    }

    public Long getCurrentPlatfomrMoney() {
        return currentPlatfomrMoney;
    }

    public void setCurrentPlatfomrMoney(Long currentPlatfomrMoney) {
        this.currentPlatfomrMoney = currentPlatfomrMoney;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getRealityName() {
        return realityName;
    }

    public void setRealityName(String realityName) {
        this.realityName = realityName;
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

    public Integer getTypes() {
        return types;
    }

    public void setTypes(Integer types) {
        this.types = types;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getThirdCode() {
        return thirdCode;
    }

    public void setThirdCode(String thirdCode) {
        this.thirdCode = thirdCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
