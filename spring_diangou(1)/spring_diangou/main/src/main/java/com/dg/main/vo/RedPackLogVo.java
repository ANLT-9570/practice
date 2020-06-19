package com.dg.main.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class RedPackLogVo implements Serializable {
    private Long id;
    private Long userId;
    private Long takeMoney;//取钱
    private Long leftMoney;//剩下的钱
    private String redPackCode;
    private Integer isTake=2;
    private Timestamp takeTime=null;
    private Long shopId;//店铺id
    private Long specificationsId;//商品id
    private String ShopName;

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

    public Long getTakeMoney() {
        return takeMoney;
    }

    public void setTakeMoney(Long takeMoney) {
        this.takeMoney = takeMoney;
    }

    public Long getLeftMoney() {
        return leftMoney;
    }

    public void setLeftMoney(Long leftMoney) {
        this.leftMoney = leftMoney;
    }

    public String getRedPackCode() {
        return redPackCode;
    }

    public void setRedPackCode(String redPackCode) {
        this.redPackCode = redPackCode;
    }

    public Integer getIsTake() {
        return isTake;
    }

    public void setIsTake(Integer isTake) {
        this.isTake = isTake;
    }

    public Timestamp getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(Timestamp takeTime) {
        this.takeTime = takeTime;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getSpecificationsId() {
        return specificationsId;
    }

    public void setSpecificationsId(Long specificationsId) {
        this.specificationsId = specificationsId;
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }
}
