package com.dg.main.Entity.logs;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.NavigableMap;

//用户提前平台币记录
@Entity
@Table(name = "user_withdraw_platform_money_log")
public class UserWithdrawPlatformMoneyLog  implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7679879197658372274L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "user_id")
    private Long userId;//用户id
    @Column(name = "create_time")
    private Date createTime=new Date();//创建时间
    @Column(name = "current_rate")
    private Long currentRate;//当日汇率
    @Column(name = "withdraw_stream_code")
    private String withdrawStreamCode;//取钱编码
    @Column(name = "platform_money")
    private Long platformMoney;//平台的钱
    @Column(name = "json_of_current_status")
    private String jsonOfCurrentStatus;//当前状态
    @Column(name = "json_of_third_response")
    private String jsonOfThirdReponse;
    @Column(name = "third_code")
    private String  thirdCode;
    @Column(name = "third_number")
    private String thirdNumber;
    public String getJsonOfCurrentStatus() {
        return jsonOfCurrentStatus;
    }

    public void setJsonOfCurrentStatus(String jsonOfCurrentStatus) {
        this.jsonOfCurrentStatus = jsonOfCurrentStatus;
    }

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

    public Long getCurrentRate() {
        return currentRate;
    }

    public void setCurrentRate(Long currentRate) {
        this.currentRate = currentRate;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



    public String getWithdrawStreamCode() {
        return withdrawStreamCode;
    }

    public void setWithdrawStreamCode(String withdrawStreamCode) {
        this.withdrawStreamCode = withdrawStreamCode;
    }

    public Long getPlatformMoney() {
        return platformMoney;
    }

    public void setPlatformMoney(Long platformMoney) {
        this.platformMoney = platformMoney;
    }

    public UserWithdrawPlatformMoneyLog() {
    }
}
