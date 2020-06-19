package com.dg.main.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrdersStatus {
    @ApiModelProperty("fewwfwef")
    private Long notPayTotal;
    private Long notShipTotal;
    private Long notReceiptTotal;
    private Long notEvaluateTotal;

    Long todayNotShipTotal = 0l;
    Long todayRevenue = 0l;
    Long todayRefund=0l;
    Long todayRecords=0l;

}
