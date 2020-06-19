package com.dg.main.Entity.settings;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
//人民币转平台币是比率
@Table(name = "money_trans_to_platform_money_rate")
@Entity
public class MoneyTransToPlatformMoneyRate  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "create_time")
    private Date createTime=new Date();
    @Column(name = "rate")
    private Long rate;
    @Column(name = "modify_time")
    private Date modifyTime=new Date();
    @Column(name = "types")
    private Integer types=1;

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

    public MoneyTransToPlatformMoneyRate() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}
