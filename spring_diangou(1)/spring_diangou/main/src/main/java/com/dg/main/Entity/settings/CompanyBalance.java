package com.dg.main.Entity.settings;

import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
//公司账户
@Table(name = "company_balance")
@Entity
public class CompanyBalance  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "money")
    private Long money=0l;
    @Column(name = "platform_money")
    private Long platformMoney=0l;
    @Column(name = "create_time")
    private Date createTime=new Date();
    @Column(name = "modify_time")
    private Date modifyTime=new Date();
    @Column(name = "types")
    private Integer types=1;
    @Column(name ="zhifubao_money")
    private Long zhifubaoMoney=0l;
    @Column(name = "weixin_money")
    private Long weixinMoney=0l;
    @Column(name ="version")
    private Long version=0l;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getZhifubaoMoney() {
        return zhifubaoMoney;
    }

    public void setZhifubaoMoney(Long zhifubaoMoney) {
        this.zhifubaoMoney = zhifubaoMoney;
    }

    public Long getWeixinMoney() {
        return weixinMoney;
    }

    public void setWeixinMoney(Long weixinMoney) {
        this.weixinMoney = weixinMoney;
    }

    public Integer getTypes() {
        return types;
    }

    public void setTypes(Integer types) {
        this.types = types;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMoney() {
        return money;
    }

    public Long getPlatformMoney() {
        return platformMoney;
    }

    public void setPlatformMoney(Long platformMoney) {
        this.platformMoney = platformMoney;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "CompanyBalance{" +
                "id=" + id +
                ", money=" + money +
                ", platformMoney=" + platformMoney +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }

    public CompanyBalance() {
    }


}
