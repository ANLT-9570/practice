package com.dg.main.Entity.logs;

import lombok.Data;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.io.Serializable;

import java.sql.Timestamp;
import java.util.Date;

//红包记录
@Entity
@Table(name = "red_pack_log")
@Data
public class RedPackLog   implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "take_money")
    private Long takeMoney;//取钱
    @Column(name = "left_money")
    private Long leftMoney;//剩下的钱
    @Column(name = "red_pack_code")
    private String redPackCode;
    @Column(name = "is_take")
    private Integer isTake=2;
    @Column(name = "take_time")
    private Timestamp takeTime=null;

    @Column(name = "shop_id")
    private Long shopId;//店铺id

    @Column(name = "specifications_id")
    private Long specificationsId;//商品id

    public Timestamp getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(Timestamp takeTime) {
        this.takeTime = takeTime;
    }

    public Integer getIsTake() {
        return isTake;
    }

    public void setIsTake(Integer isTake) {
        this.isTake = isTake;
    }

    private Timestamp createTime=new Timestamp(new Date().getTime());
    public Long getId() {
        return id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
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

    public RedPackLog() {
    }
}
