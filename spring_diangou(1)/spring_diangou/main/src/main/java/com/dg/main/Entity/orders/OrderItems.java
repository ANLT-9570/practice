package com.dg.main.Entity.orders;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.cfg.EJB3NamingStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
@Entity
@Table(name = "order_items")
@Data
@ToString
@NoArgsConstructor
public class OrderItems   implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "orders_id")
    private Long ordersId;
    @Column(name = "shop_id")
    private Long shopId;
    @Column(name = "specification_id")
    private Long specificationId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "seller_id")
    private Long sellerId;
@Column(name = "number")
private Long number;


    @Column(name = "is_refunding")
    private Integer isRefunding=2;
    @Column(name = "create_time")
    private Timestamp createTime=new Timestamp(new Date().getTime());
    @Column(name = "send_time")
    private Timestamp sendTime=null;
    @Column(name = "refund_time")
    private Timestamp refundTime=null;
    @Column(name = "pay_time")
    private Timestamp payTime=null;
    @Column(name = "modify_time")
    private Timestamp modifyTime=new Timestamp(new Date().getTime());
    @Column(name = "phase")
    private Integer phase=1;//1.代付款 2.已付款 3.代发货 10.交易完成  //11。7天退货完成
    @Column(name = "is_refund_in_phase_one")
    private Integer isRefundInPhaseOne=2;
    @Column(name = "is_refund_in_phase_two")
    private Integer isRefundInPhaseTwo=2;
    @Column(name = "is_refund_in_phase_three")
    private Integer isRefundInPhaseThree=2;
    @Column(name = "is_callback_success")
    private Integer isCallbackSuccess=2;
    @Column(name = "is_business_sended_in_phase_three")
    private Integer isBusinessSendedInPhaseThree=2;
    @Column(name = "is_refund_finished")
    private Integer isRefundFinished=2;
    @Column(name = "is_customer_deliveried_in_phase_three")
    private Integer isCustomerDeliveriedInPhaseThree=2;

    @Column(name = "mark")
    private String mark;
    @Column(name = "img")
    private String img;
    @Column(name = "coupon_id")
    private Long couponId;
    @Column(name = "shop_car_id")
    private Long shopCarId;

    @Column(name = "color")
    private String color;
    @Column(name = "finished_time")
    private Timestamp finishedTime=null;



    @Column(name = "is_refund_in_phase_ten")
    private Integer isRefundInPhaseTen=2;
    @Column(name = "is_buyer_sended_in_phase_ten")
    private Integer isBuyerSendedInPhaseTen=2;
    @Column(name = "is_seller_deliveried_in_phase_ten")
    private Integer isSellerDeliveriedInPhaseTen=2;
    @Column(name = "seller_income_platform_money")
    private Long sellerImcomePlatformMoney=0l;
    @Column(name = "is_seller_agree_in_phase_ten")
    private Integer isSellerAgreeInPhaseTen=0;
    @Column(name = "refund_reason")
    private String refundReason="";

//    @Column(name = "unite_code")
//    private String uniteCode;
//    @Column(name = "unite_money")
//    private Long uniteMoney;
//    @Column(name = "unite_platform_money")
//    private Long unitePlatformMoney;
    @Column(name = "schedule_time")
    private Timestamp scheduleTime=null;

    @Column(name = "is_post_money_free")
    private Integer isPostMoneyFree=2;
    @Column(name = "is_deleted")
    private Integer isDeleted=2;
    @Column(name = "is_seller_agree_in_phase_two")
    private Integer isSellerAgreeInPhaseTwo=0;
    @Column(name = "size_id")
    private Long sizeId;
    @Column(name = "logistics_code")
    private String logisticsCode;
    @Column(name = "logistics_type")
    private String logisticsType;
    @Column(name = "logistics_status")
    private Integer logisticsStatus;
    @Column(name = "is_seven_days_refund")
    private Integer isSevenDaysRefund=2;

    @Column(name = "address_id")
    private Long addressId;
    @Column(name = "group_by_field")
    private String groupByField;
    @Column(name = "is_single_pay")
    private Integer isSinglePay=2;
    @Column(name = "item_code")
    private String itemCode;
    @Column(name = "third_platform_action")
    private Integer thirdPlatformAction=1;//1人民币支付 2平台币支付 3 支付宝支付 4 微信支付
    @Column(name = "is_bad")
    private Integer isBad=0;
    @Column(name = "is_normal")
    private Integer isNormal=0;
    @Column(name = "is_good")
    private Integer isGood=0;
    @Column(name = "comment_date")
    private Date commentDate=null;
    @Column(name = "discount_platform_money")
    private Long discountPlatformMoney=0l;
    @Column(name = "total_money")
    private Long totalMoney;//价格*数量
   @Column(name = "money")
    private Long money=0l; //价格
    @Column(name = "company_income")
    private Long companyIncome=0l;//公司收入
    @Column(name = "seller_income")
    private Long sellerIncome=0l;//商家收入
    @Column(name = "third_income")
    private Long thirdIncome=0l;//第三方收入
    @Column(name = "pay_money")
    private Long payMoney=0l;
    @Column(name = "platform_money")
    private Long platformMoney=0l;//点币
    @Column(name = "pay_platform_money")
    private Long payPlatformMoney=0l;//用户点币购买的支出
    @Column(name = "post_money")
    private Long postMoney=0l;//邮费
    @Column(name = "discount_money")
    private Long discountMoney=0l;//折扣
    @Column(name = "total_platform_money")
    private Long totalPlatformMoney;
    @Column(name = "rated_platform_money")
    private Long RatedPlatformMoney; //乘以总点币后的数


}
