package com.dg.main.Entity.logs;

import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "seller_buy_user_third_pay_log")
public class SellerBuyUseThirdPayLog   implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "income")
    private Long income=0l;
    @Column(name = "zhifubao_income")
    private Long zhifubaoIncome=0l;
    @Column(name = "weinxin_income")
    private Long weixinIncome=0l;
    @Column(name = "day_of_year")
    private Integer dayOfyear;
    @Column(name = "months")
    private Integer months;
    @Column(name = "years")
    private Integer years;
    @Column(name = "create_time")
    private Date createTime=new Date();
  //  private Long userId;


    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIncome() {
        return income;
    }

    public void setIncome(Long income) {
        this.income = income;
    }

    public Long getZhifubaoIncome() {
        return zhifubaoIncome;
    }

    public void setZhifubaoIncome(Long zhifubaoIncome) {
        this.zhifubaoIncome = zhifubaoIncome;
    }

    public Long getWeixinIncome() {
        return weixinIncome;
    }

    public void setWeixinIncome(Long weixinIncome) {
        this.weixinIncome = weixinIncome;
    }

    public Integer getDayOfyear() {
        return dayOfyear;
    }

    public void setDayOfyear(Integer dayOfyear) {
        this.dayOfyear = dayOfyear;
    }


    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public SellerBuyUseThirdPayLog() {
    }
}
