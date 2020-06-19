package com.dg.main.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Data
public class PartnerHomeDTO {
    private String shopName;
    private String image;
    private String shopsInto;//商户进件
    private String todayEarnings;//今日收益
    private String todayTransaction;//今日交易
    private String monthSum;//本月交易额
    private String comradeSum;//本月累计伙伴
    private String shopsSum;//本月累计商户
    private String directSum;//直营交易额
    private String subSum;//下级交易额
    private String count;//合计
    private String newAddShops;//新增商户
    private String directNewAdd;//直营新增
    private String subNewAdd;//下级新增
}
