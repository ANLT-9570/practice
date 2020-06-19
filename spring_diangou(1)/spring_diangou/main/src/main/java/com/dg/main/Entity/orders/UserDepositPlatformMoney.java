package com.dg.main.Entity.orders;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
//用户存入平台币
@Entity
@Table(name = "user_deposit_platform_money")
public class UserDepositPlatformMoney  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "create_time")
    private Timestamp createTime=new Timestamp(new Date().getTime());
    @Column(name = "platform_money")
    private Long platformMoney;
    @Column(name = "is_success")
    private Integer isSuccess=2;
    @Column(name = "direction")
    private Integer direction=1; //支付方式
    @Column(name = "deposit_stream_code")
    private String depositStreamCode;
    @Column(name = "third_code")
    private String  thirdCode;
    @Column(name = "third_number")
    private String thirdNumber;
    @Column(name = "left_money")
    private Long leftMoney=0l;
    @Column(name = "money")
    private Long money;

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
}
