package com.dg.main.Entity.logs;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
//公司现金流记录
@Entity
@Table(name = "company_money_stream_log")
public class CompanyMoneyStreamLog  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "json_of_previous_company_balace")
    private String jsonOfPreviousCompanyBalance;
    @Column(name = "json_of_current_company_balance")
    private String jsonOfCurrentCompanyBalance;
    @Column(name = "seller_id")
    private Long sellerId;
    @Column(name = "buyer_id")
    private Long buyerId;
    @Column(name = "status")
    private Integer status=1;//状态  1.支付宝 2。微信
    @Column(name = "types")
    private Integer types=1;//类型   1.订单  2.提现
    @Column(name = "json_of_user_balance")
    private String jsonOfUserBalance;
    @Column(name = "money")
    private Long money=0l;
    @Column(name = "code")
    private String code;
    @Column(name = "income_platform_money")
    private Long incomePlatformMoney=0l;
    @Column(name = "income_money")
    private Long incomeMoney=0l;

    public Long getIncomePlatformMoney() {
        return incomePlatformMoney;
    }

    public void setIncomePlatformMoney(Long incomePlatformMoney) {
        this.incomePlatformMoney = incomePlatformMoney;
    }

    public Long getIncomeMoney() {
        return incomeMoney;
    }

    public void setIncomeMoney(Long incomeMoney) {
        this.incomeMoney = incomeMoney;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    private Date createTime=new Date();

    public Integer getTypes() {
        return types;
    }

    public void setTypes(Integer types) {
        this.types = types;
    }

//    public Integer getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Integer userId) {
//        this.userId = userId;
//    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getJsonOfUserBalance() {
        return jsonOfUserBalance;
    }

    public void setJsonOfUserBalance(String jsonOfUserBalance) {
        this.jsonOfUserBalance = jsonOfUserBalance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJsonOfPreviousCompanyBalance() {
        return jsonOfPreviousCompanyBalance;
    }

    public void setJsonOfPreviousCompanyBalance(String jsonOfPreviousCompanyBalance) {
        this.jsonOfPreviousCompanyBalance = jsonOfPreviousCompanyBalance;
    }

    public String getJsonOfCurrentCompanyBalance() {
        return jsonOfCurrentCompanyBalance;
    }

    public void setJsonOfCurrentCompanyBalance(String jsonOfCurrentCompanyBalance) {
        this.jsonOfCurrentCompanyBalance = jsonOfCurrentCompanyBalance;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
