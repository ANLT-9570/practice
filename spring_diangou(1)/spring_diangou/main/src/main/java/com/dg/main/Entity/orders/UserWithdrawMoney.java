package com.dg.main.Entity.orders;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
//用户提前人民币
@Entity
@Table(name = "user_withdraw_money")
public class UserWithdrawMoney  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "withdraw_stream_code")
    private String withdrawStreamCode;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "money")
    private Long money;
    @Column(name = "left_money")
    private Long leftMoney;
    @Column(name = "create_time")
    private Timestamp createTime=new Timestamp(new Date().getTime());
    @Column(name = "is_success")
    private Integer isSuccess=2;
    @Column(name = "current_rate")
    private Long currentRate;
    @Column(name = "third_code")
    private String  thirdCode;
    @Column(name = "third_number")
    private String thirdNumber;
    @Column(name = "direction")
    private Integer direction=1;
    @Column(name = "third_info_id")
    private Long thirdInfoId;
    @Column(name = "is_audit")
    private Integer isAudit=2;
    @Column(name = "audit_failed")
    private String auditFailed="";

    public Integer getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(Integer isAudit) {
        this.isAudit = isAudit;
    }

    public String getAuditFailed() {
        return auditFailed;
    }

    public void setAuditFailed(String auditFailed) {
        this.auditFailed = auditFailed;
    }
    public Long getLeftMoney() {
        return leftMoney;
    }

    public void setLeftMoney(Long leftMoney) {
        this.leftMoney = leftMoney;
    }

    public Long getThirdInfoId() {
        return thirdInfoId;
    }

    public void setThirdInfoId(Long thirdInfoId) {
        this.thirdInfoId = thirdInfoId;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
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

    public UserWithdrawMoney() {
    }

    @Override
    public String toString() {
        return "UserWithdrawMoney{" +
                "id=" + id +
                ", withdrawStreamCode='" + withdrawStreamCode + '\'' +
                ", userId=" + userId +
                ", money=" + money +
                ", createTime=" + createTime +
                '}';
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
