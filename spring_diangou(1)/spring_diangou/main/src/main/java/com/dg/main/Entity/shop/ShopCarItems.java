package com.dg.main.Entity.shop;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
@Entity
@Table(name = "shop_car_items")
@Data
@ToString
@NoArgsConstructor
public class ShopCarItems   implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "shop_car_id")
    private Long shopCarId;
    @Column(name = "number")
    private Long number;
    @Column(name = "platform_money")
    private Long platformMoney;
    @Column(name = "money")
    private Long money;
    @Column(name = "specification_id")
    private Long specificationId;
    @Column(name = "sku_id")
    private Long skuId;
    @Column(name = "create_time")
    private Timestamp createTime=new Timestamp(new Date().getTime());


}
