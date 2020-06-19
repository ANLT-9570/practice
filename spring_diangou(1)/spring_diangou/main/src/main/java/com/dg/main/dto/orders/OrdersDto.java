package com.dg.main.dto.orders;

import com.dg.main.Entity.orders.OrderItems;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.compress.utils.Lists;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
@Data
@ToString
@NoArgsConstructor
public class OrdersDto {
    private Long id;

    private Long customerId; // 客户id

    private Long businessId; // 商家ID
    private Timestamp payTime=null;
    private String orderCode;
    private Integer thirdPlatformAction=1;
    private Long shopId;
    private List<OrderItemsDto> items= Lists.newArrayList();
    private Integer phase;
    private String shopName;

    private Integer isRefundFinished=2;

    private Integer isSinglePay=2;
    private Long price;
    private String username="";
    private Long specificationId;
    private Date createTime=null;
    private Date sendTime=null;
    private String mark="";
    private String logisticsCode;
    private String logisticsType;


}

