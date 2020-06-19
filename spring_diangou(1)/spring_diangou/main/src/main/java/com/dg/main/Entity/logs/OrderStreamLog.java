package com.dg.main.Entity.logs;

import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
//订单状态改变记录
@Entity
@Table(name = "order_steam_log")
public class OrderStreamLog   implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "json_state_of_orders",columnDefinition = "json")
    private String jsonStateOfOrders;
    @Column(name = "json_state_of_modified_orders",columnDefinition = "json")
    private String jsonStateOfModifiedOrders;

    public OrderStreamLog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Integer currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Long getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(Long customerId) {
        CustomerId = customerId;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getJsonStateOfOrders() {
        return jsonStateOfOrders;
    }

    public void setJsonStateOfOrders(String jsonStateOfOrders) {
        this.jsonStateOfOrders = jsonStateOfOrders;
    }

    public String getJsonStateOfModifiedOrders() {
        return jsonStateOfModifiedOrders;
    }

    public void setJsonStateOfModifiedOrders(String jsonStateOfModifiedOrders) {
        this.jsonStateOfModifiedOrders = jsonStateOfModifiedOrders;
    }
}
