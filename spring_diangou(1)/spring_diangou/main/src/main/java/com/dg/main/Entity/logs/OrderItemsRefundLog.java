package com.dg.main.Entity.logs;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order_items_refund_log")
@Data
@ToString
@NoArgsConstructor
public class OrderItemsRefundLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "create_time")
    private Date createTime=new Date();
    @Column(name = "current_status")
    private Integer currentStatus;
    @Column(name = "customer_id")
    private Long CustomerId; // 客户id
    @Column(name = "business_id")
    private Long businessId; // 商家ID
    @Column(name = "order_code")
    private String orderCode;//订单编号
    @Column(name = "money")
    private Long money=0l;//账户的钱
    @Column(name = "platform_money")
    private Long platformMoney=0l;//平台的钱
    @Column(name = "is_refund")
    private Integer isRefund=2;//1,退款，2,未退款
    @Column(name = "json_state_of_orders")
    private String jsonStateOfOrders;
    @Column(name = "json_state_of_modified_orders")
    private String jsonStateOfModifiedOrders;
}
