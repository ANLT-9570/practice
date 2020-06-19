package com.dg.main.dto.orders;

import lombok.Data;

import javax.persistence.Column;
@Data
public class OrderInfoDto {

    private Integer logisticsStatus;

    private String logisticsCode;

    private String logisticsType;
    private Long addressId;
    private String address ;  //详细地址
    private String city;
    private String province;
    private String phone;         //电话
    private String consignee;    //收货人

}
