package com.dg.main.vo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CouponItemVo {

    private Long id;
    private Long userId;
    private Long couponId;
    private Long shopId;
    private Long specificationId;//商品id
    private Timestamp startTime;//开始时间
    private Timestamp endTime;//结束时间
    private Long preferentialPrice;//优惠价格
    private Long conditions;//条件

    private String shpopName;
    private String displayImg;

}
