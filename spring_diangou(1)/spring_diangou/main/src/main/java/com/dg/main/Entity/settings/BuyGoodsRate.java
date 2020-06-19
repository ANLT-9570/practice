package com.dg.main.Entity.settings;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
//购买物品是的比例
@Table(name = "buy_goods_rate")
@Entity
public class BuyGoodsRate  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "rate")
    private Long rate;
    @Column(name = "types")
    private Integer types=1;
    @Column(name = "create_time")
    private Date createTime=new Date();

    public Integer getTypes() {
        return types;
    }

    public void setTypes(Integer types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return "BuyGoodsRate{" +
                "id=" + id +
                ", rate=" + rate +
                ", createTime=" + createTime +
                '}';
    }

    public BuyGoodsRate() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
