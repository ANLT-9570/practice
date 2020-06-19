package com.dg.main.dto.shop;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
@ToString
@NoArgsConstructor
public class ShopCarItemsDao {

    private Long id;

    private Long shopCarId;

    private Long number;

    private Long platformMoney;

    private Long money;

    private Long specificationId;

    private Long skuId;
    private String info;
    private  String img;
    private String name;

}
