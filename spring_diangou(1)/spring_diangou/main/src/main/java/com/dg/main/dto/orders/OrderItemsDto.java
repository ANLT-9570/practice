package com.dg.main.dto.orders;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
@Data
@ToString
@NoArgsConstructor
public class OrderItemsDto {

    private Long shopId;

    private Long specificationId;

    private Long userId;

    private Long sellerId;

    private Long number;

    private Integer phase=1;//1.代付款 2.已付款 3.代发货 10.交易完成  //11。7天退货完成
    private String describe;

    private String mark;

    private String img;


    private Long shopCarId;

    private Long sizeId;

    private Integer isBad=0;

    private Integer isNormal=0;

    private Integer isGood=0;

    private Long money=0l; //价格
    private String specificationName;
}
