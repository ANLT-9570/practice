package com.dg.main.Entity.logs;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
//订单退款的记录
@Entity
@Table(name = "order_refund_log")
public class OrderRefundLog  implements Serializable {
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

    @Override
    public String toString() {
        return "OrderRefundLog{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", currentStatus=" + currentStatus +
                ", CustomerId=" + CustomerId +
                ", businessId=" + businessId +
                ", orderCode='" + orderCode + '\'' +
                ", money=" + money +
                ", platformMoney=" + platformMoney +
                ", isRefund=" + isRefund +
                ", jsonStateOfOrders='" + jsonStateOfOrders + '\'' +
                ", jsonStateOfModifiedOrders='" + jsonStateOfModifiedOrders + '\'' +
                '}';
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

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Long getPlatformMoney() {
        return platformMoney;
    }

    public void setPlatformMoney(Long platformMoney) {
        this.platformMoney = platformMoney;
    }

    public Integer getIsRefund() {
        return isRefund;
    }

    public void setIsRefund(Integer isRefund) {
        this.isRefund = isRefund;
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

    public OrderRefundLog() {
    }
}
