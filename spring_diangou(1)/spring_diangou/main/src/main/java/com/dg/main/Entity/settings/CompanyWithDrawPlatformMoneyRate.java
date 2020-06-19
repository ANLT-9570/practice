package com.dg.main.Entity.settings;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
//用户提现平台币是比率
@Table(name = "company_withdraw_platform_money_rate")
@Entity
public class CompanyWithDrawPlatformMoneyRate  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "create_time")
    private Date createTime=new Date();
    @Column(name = "rate")
    private Long rate;
    @Column(name = "modify_time")
    private Date modifyTime=new Date();
    @Column(name = "types")
    private Integer types=1;
    public Date getModifyTime() {
        return modifyTime;
    }

    public Integer getTypes() {
        return types;
    }

    public void setTypes(Integer types) {
        this.types = types;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "CompanyWithDrawPlatformMoneyRate{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", rate=" + rate +
                ", modifyTime=" + modifyTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }

    public CompanyWithDrawPlatformMoneyRate() {
    }
}
