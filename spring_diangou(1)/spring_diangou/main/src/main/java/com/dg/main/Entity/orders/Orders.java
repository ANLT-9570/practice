package com.dg.main.Entity.orders;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
//订单
@Entity
@Table(name = "orders")
@Data
@ToString
@NoArgsConstructor
public class Orders  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "customer_id")
    private Long customerId; // 客户id
    @Column(name = "business_id")
    private Long businessId; // 商家ID
    @Column(name = "order_code")
    private String orderCode;
    @Column(name = "shop_id")
    private Long shopId;
    @Column(name = "third_platform_action")
    private Integer thirdPlatformAction=1;//1人民币支付 2平台币支付 3 支付宝支付 4 微信支付
    @Column(name = "phase")
    private Integer phase=1;
//    private Integer isRefundFinished=1;


    @Column(name = "logistics_status")
    private Integer logisticsStatus;
    @Column(name = "logistics_code")
    private String logisticsCode;
    @Column(name = "logistics_type")
    private String logisticsType;

    @Column(name = "address_id")
    private Long addressId;
    @Column(name = "is_delete")
    private Integer isDelete=2;
    @Column(name = "mark")
    private String mark="";

    @Column(name = "create_time")
    private Date createTime=new Date();
    @Column(name = "send_time")
    private Timestamp sendTime=null;
    @Column(name = "refund_time")
    private Timestamp refundTime=null;
    @Column(name = "pay_time")
    private Timestamp payTime=null;
    @Column(name = "modify_time")
    private Timestamp modifyTime=null;
    @Column(name = "schedule_time")
    private Timestamp scheduleTime=null;
    @Column(name = "deliveryTime")
    private Timestamp deliveryTime=null;


    @Column(name = "third_orders_number")
    private String thirdOrdersNumber;
    @Column(name = "is_callback_success")
    private Integer isCallbackSuccess=2;
    @Column(name = "refund_reason")
    private String refundReason="";
    @Column(name = "is_refund_finished")
    private Integer isRefundFinished=2;
    @Column(name = "is_single_pay")
    private Integer isSinglePay=2;
    @Column(name = "is_seller_agree_in_phase_two")
    private Integer isSellerAgreeInPhaseTwo=0;
    @Column(name = "is_business_sended_in_phase_three")
    private Integer isBusinessSendedInPhaseThree=2;
    @Column(name = "total_third_income")
    private Long totalThirdIncome;//第三方收入
    @Column(name = "total_money")
    private Long totalMoney=0l;//总人民币支付
    @Column(name = "total_plat_form_money")
    private Long totalPlatformMoney=0l;//总点币支付
    @Column(name = "send_money")
    private Long postMoney=0l; //邮费
    @Column(name = "company_income")
    private Long totalCompanyIncome;//公司收入
    @Column(name = "total_seller_income")
    private Long totalSellerIncome;//商家收入
    @Column(name = "total_user_pay_platform_money")
    private Long totalUserPayPlatformMoney;//用户点币购买支付
    @Column(name = "is_direct_to_pay")
    private Integer isDirectToPay=2;
    @Column(name = "direct_code")
    private String directCode="";
    @Column(name = "coupon_id")
    private Long couponId;
    @Column(name = "coupon_money")
    private Long couponMoney;

    @Column(name = "ranks")
    private Integer ranks=0;
    @Column(name = "comment")
    private String comment;
    @Column(name = "comment_original_img")
    private String commentOriginalImg;
    @Column(name = "comment_thumb_img")
    private String commentThumbImg;
    @Column(name = "comment_date")
    private Date commentDate;
    @Column(name = "thirdCode")
    private String thirdCode;
    @Column(name = "is_refunding")
    private Integer isRefunding=2;
    @Column(name = "payed_years")
    private Integer payedYears;
    @Column(name = "payed_month")
    private Integer payedMonth;
    @Column(name = "payed_day")
    private Integer payedDay;
    @Column(name = "refund_years")
    private Integer refundYears;
    @Column(name = "refund_month")
    private Integer refundMonth;
    @Column(name = "refund_day")
    private Integer refundDay;

    @Column(name = "delivary_years")
    private Integer deliveryYears;
    @Column(name = "delivary_month")
    private Integer deliveryMonth;
    @Column(name = "delivary_day")
    private Integer deliveryDay;
}
