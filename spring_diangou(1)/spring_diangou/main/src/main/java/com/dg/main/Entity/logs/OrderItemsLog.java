package com.dg.main.Entity.logs;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order_items_log")
@Data
@ToString
@NoArgsConstructor
public class OrderItemsLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "create_time")
    private Date createTime=new Date();
    @Column(name = "current_status")
    private Integer currentStatus;//订单状态1，待付款，2，已付款，3，待发货
    @Column(name = "customer_id")
    private Long CustomerId; // 客户id
    @Column(name = "business_id")
    private Long businessId; // 商家ID
    @Column(name = "order_code")
    private String orderCode;
    @Column(name = "new_status_of_item",columnDefinition = "json")
    private String newStatusOfItem;
    @Column(name = "old_status_of_item",columnDefinition = "json")
    private String oldStatusOfItem;
}
