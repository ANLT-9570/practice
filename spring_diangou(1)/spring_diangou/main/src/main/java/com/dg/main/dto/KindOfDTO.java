package com.dg.main.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Data
public class KindOfDTO {

    private String directSum;//直营交易额
    private String subSum;//下级交易额
    private String count;//合计交易额
    private String subNewAdd;//下级新增
    private String newAdd;//新增商户
    private String actualNewAdd;//直营新增商户
    private String newRefunded;//新增退货
    private String directRefunded;//直营退货
    private String subRefunded;//下级退货

}
