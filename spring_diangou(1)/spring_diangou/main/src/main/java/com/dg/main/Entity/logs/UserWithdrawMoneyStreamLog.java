package com.dg.main.Entity.logs;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.NavigableMap;

//用户提前人民币记录
@Entity
@Table(name = "user_withdraw_money_stream_log")
public class UserWithdrawMoneyStreamLog  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;//用户
    @Column(name = "money")
    private Long money;//钱
    @Column(name = "create_time")
    private Date createTime=new Date();//
    @Column(name = "withdraw_stream_code")
    private String withdrawStreamCode;//取流代码
    @Column(name = "json_of_current_status")
    private String jsonOfCurrentStatus;//当前状态
    @Column(name = "json_of_third_response")
    private String jsonOfThirdReponse;
    @Column(name = "third_code")
    private String  thirdCode;
    @Column(name = "third_number")
    private String thirdNumber;

    public String getJsonOfThirdReponse() {
        return jsonOfThirdReponse;
    }

    public void setJsonOfThirdReponse(String jsonOfThirdReponse) {
        this.jsonOfThirdReponse = jsonOfThirdReponse;
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

    public String getJsonOfCurrentStatus() {
        return jsonOfCurrentStatus;
    }

    public void setJsonOfCurrentStatus(String jsonOfCurrentStatus) {
        this.jsonOfCurrentStatus = jsonOfCurrentStatus;
    }

    public String getWithdrawStreamCode() {
        return withdrawStreamCode;
    }

    public void setWithdrawStreamCode(String withdrawStreamCode) {
        this.withdrawStreamCode = withdrawStreamCode;
    }

    @Override
    public String toString() {
        return "UserWithdrawMoneyStreamLog{" +
                "id=" + id +
                ", userId=" + userId +
                ", money=" + money +
                ", createTime=" + createTime +
                ", withdrawStreamCode='" + withdrawStreamCode + '\'' +
                ", jsonOfCurrentStatus='" + jsonOfCurrentStatus + '\'' +
                ", jsonOfThirdReponse='" + jsonOfThirdReponse + '\'' +
                ", thirdCode='" + thirdCode + '\'' +
                ", thirdNumber='" + thirdNumber + '\'' +
                '}';
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

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public UserWithdrawMoneyStreamLog() {
    }
}
