package com.dg.main.Entity.logs;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
//用户存入平台币记录
@Entity
@Table(name = "user_deposit_platform_money_log")
public class UserDepositPlatformMoneyLog  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "money")
    private Long money;
    @Column(name = "json_of_user_deposit_platform_money")
    private String jsonOfUserDepositPlatformMoney;
    @Column(name = "create_time")
    private Timestamp createTime=new Timestamp(new Date().getTime());
    @Column(name = "deposit_code")
    private String depositCode;//存放代码
    @Column(name = "third_code")
    private String  thirdCode;
    @Column(name = "third_number")
    private String thirdNumber;
    @Column(name = "json_of_third_reponse")
    private String jsonOfThirdReponse;
    @Column(name = "left_money")
    private Long leftMoney;

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

    public String getJsonOfThirdReponse() {
        return jsonOfThirdReponse;
    }

    public void setJsonOfThirdReponse(String jsonOfThirdReponse) {
        this.jsonOfThirdReponse = jsonOfThirdReponse;
    }

    public String getDepositCode() {
		return depositCode;
	}

	public void setDepositCode(String depositCode) {
		this.depositCode = depositCode;
	}

	public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserDepositPlatformMoneyLog{" +
                "id=" + id +
                ", userId=" + userId +
                ", money=" + money +
                ", jsonOfUserDepositPlatformMoney='" + jsonOfUserDepositPlatformMoney + '\'' +
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

    public String getJsonOfUserDepositPlatformMoney() {
        return jsonOfUserDepositPlatformMoney;
    }

    public void setJsonOfUserDepositPlatformMoney(String jsonOfUserDepositPlatformMoney) {
        this.jsonOfUserDepositPlatformMoney = jsonOfUserDepositPlatformMoney;
    }
}
