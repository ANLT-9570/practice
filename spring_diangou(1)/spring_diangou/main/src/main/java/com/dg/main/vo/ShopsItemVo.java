package com.dg.main.vo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ShopsItemVo {

    private Long conditions;
    private Long preferentialPrice;
    private Timestamp startTime;//开始时间
    private Timestamp endTime;//结束时间

}
