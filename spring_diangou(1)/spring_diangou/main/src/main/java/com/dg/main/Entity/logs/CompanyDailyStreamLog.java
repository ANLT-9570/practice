package com.dg.main.Entity.logs;

import org.omg.CORBA.INTERNAL;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "company_daily_stream_log")
public class CompanyDailyStreamLog   implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "types")
    private Integer types=0;
    @Column(name = "day_of_year")
    private Integer dayOfyear;
    @Column(name = "month")
    private Integer month;
    @Column(name = "years")
    private Integer years;
    @Column(name = "create_time")
    private Date createTime=new Date();
    @Column(name = "income")

    private Long income=0l;
    @Column(name = "outcome")
    private Long outcome=0l;
    @Column(name = "code")
    private String code;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "zhifubao_income")
    private Long zhifubaoIncome=0l;
    @Column(name = "weinxin_income")
    private Long weixinIncome=0l;
    @Column(name = "zhifubao_outcome")
    private Long zhifubaoOutcome=0l;
    @Column(name = "weixin_outcome")
    private Long weixinOutcome=0l;
    @Column(name = "buy_outcome")
    private Long buyOutcome=0l;

    public Long getBuyOutcome() {
        return buyOutcome;
    }

    public void setBuyOutcome(Long buyOutcome) {
        this.buyOutcome = buyOutcome;
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

    public Long getZhifubaoOutcome() {
        return zhifubaoOutcome;
    }

    public void setZhifubaoOutcome(Long zhifubaoOutcome) {
        this.zhifubaoOutcome = zhifubaoOutcome;
    }

    public Long getWeixinOutcome() {
        return weixinOutcome;
    }

    public void setWeixinOutcome(Long weixinOutcome) {
        this.weixinOutcome = weixinOutcome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTypes() {
        return types;
    }

    public void setTypes(Integer types) {
        this.types = types;
    }

    public Integer getDayOfyear() {
        return dayOfyear;
    }

    public void setDayOfyear(Integer dayOfyear) {
        this.dayOfyear = dayOfyear;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
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

    public Long getIncome() {
        return income;
    }

    public void setIncome(Long income) {
        this.income = income;
    }

    public Long getOutcome() {
        return outcome;
    }

    public void setOutcome(Long outcome) {
        this.outcome = outcome;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public CompanyDailyStreamLog() {
    }
}
