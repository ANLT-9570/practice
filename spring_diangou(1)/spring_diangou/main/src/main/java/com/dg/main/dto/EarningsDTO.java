package com.dg.main.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class EarningsDTO {

    private String accumulativeEarnings;//累计收益
    private String upAccumulativeEarnings;//上个月累计收益
    private String monthSum;//本月交易额
    private String monthRefunded; //本月退货额
    private String monthActualSum;//本月实际成交额

}
