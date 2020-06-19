package com.dg.main.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class ThirdPayResultDto {
    private String appId;
    private String nonceStr;
    private String packages;
    private String partnerid;
    private String returnCode;
    private String retrunMsg;
    private String sign;
    private String signType;
    private String timestamp;

    private String prepayId;

    private String zhifubao;
}
