package com.dg.main.Entity.logs;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
//用户存入人民币
@Entity
@Table(name = "user_deposit_money_log")
public class UserDepositMoneyLog  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "money")
    private Long money;
    @Column(name ="deposit_code")
    private String depositCode;
    @Column(name = "message")
    private String message;
    @Column(name = "json_of_third_reponse")
    private String jsonOfThirdReponse;
    @Column(name = "third_code")
    private String  thirdCode;
    @Column(name = "third_number")
    private String thirdNumber;
    @Column(name = "create_time")
    private Timestamp createTime=new Timestamp(new Date().getTime());
    @Column(name = "left_money")
    private Long leftMoney;

    public Long getLeftMoney() {
        return leftMoney;
    }

    public void setLeftMoney(Long leftMoney) {
        this.leftMoney = leftMoney;
    }

    public String getJsonOfThirdReponse() {
        return jsonOfThirdReponse;
    }

    public void setJsonOfThirdReponse(String jsonOfThirdReponse) {
        this.jsonOfThirdReponse = jsonOfThirdReponse;
    }

    public String getThirdNumber() {
        return thirdNumber;
    }

    public void setThirdNumber(String thirdNumber) {
        this.thirdNumber = thirdNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


//    public String getJson() {
//        return json;
//    }
//
//    public void setJson(String json) {
//        this.json = json;
//    }

    public String getJson() {
        return getJson();
    }

    public String getThirdCode() {
        return thirdCode;
    }

    public void setThirdCode(String thirdCode) {
        this.thirdCode = thirdCode;
    }

    public String getDepositCode() {
        return depositCode;
    }

    public void setDepositCode(String depositCode) {
        this.depositCode = depositCode;
    }

    private String jsonOfUserDepositMoney;


    @Override
    public String toString() {
        return "UserDepositMoneyLog{" +
                "id=" + id +
                ", userId=" + userId +
                ", money=" + money +
                ", jsonOfUserDepositMoney='" + jsonOfUserDepositMoney + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public UserDepositMoneyLog() {
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
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

    public String getJsonOfUserDepositMoney() {
        return jsonOfUserDepositMoney;
    }

    public void setJsonOfUserDepositMoney(String jsonOfUserDepositMoney) {
        this.jsonOfUserDepositMoney = jsonOfUserDepositMoney;
    }
}
