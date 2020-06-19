package com.dg.main.Entity.users;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
//优惠券
@Table(name = "coupon")
@Entity
@Data
@ToString
@NoArgsConstructor
public class Coupon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ApiModelProperty(value = "用户id",example = "1")
	@Column(name = "user_id")
    private Long userId;//用户id

	@ApiModelProperty(value = "商铺Id",example = "1")
	@Column(name = "shop_id")
    private Long shopId;//商铺Id
	@Column(name = "create_time")
	private Date createTime=new Date();
	@Column(name = "start_time")
	private Date startTime;
	@Column(name = "end_time")
	private Date endTime;
	@Column(name = "conditions")
	private String conditions;
	@Column(name = "price")
	private Long price;
	@Column(name = "condition_when")
	private Long conditionWhen;
	@Column(name = "minus_price")
	private Long minusPrice;
	@Column(name = "percent")
	private Long percent;
}
