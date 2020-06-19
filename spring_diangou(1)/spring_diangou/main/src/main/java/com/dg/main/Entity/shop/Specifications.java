package com.dg.main.Entity.shop;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

//规格
@Table(name = "specifications")
@Entity
@Data
@ToString
@NoArgsConstructor
public class Specifications  implements Serializable{
	
private static final long serialVersionUID = 2380456145842301868L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
		 private Long 	id;

	@ApiModelProperty(value = "商家id",example = "1")
	@Column(name = "user_id")
		 private Long userId; //商家id

	@ApiModelProperty(value = "描述")
	@Column(name = "describes")
		 private String  describe;//描述

	@ApiModelProperty(value = "图片")
	@Column(name = "img")
		 private String img; //图片


	@ApiModelProperty(value = "单价",example = "1")
	@Column(name = "price")
		 private Long price; //单价

	@ApiModelProperty(value = "折扣价",example = "1")
	@Column(name = "discount_price")
		 private Long discountPrice; //折扣价

	@ApiModelProperty(value = "品牌")
	@Column(name = "brand")
	private String brand; //品牌

	@ApiModelProperty(value = "数量",example = "1")
	@Column(name = "number")
		 private Long number ;// 数量

	@ApiModelProperty(value = "简介")
	@Column(name = "mydescribe")
		private String mydescribe;


	@ApiModelProperty(value = "售后服务")
	@Column(name = "sales_service")
	private String salesService;//售后服务




	@Column(name = "status")
		 private Integer status;//1上架，2下架，3删除
	@Column(name = "id_delete")
	private Integer isDelete=2;

	@Column(name = "post_money")
	private Long postMoney=0l;
	@Column(name = "logitics_type")
	private String logiticsType;
	//@ApiModelProperty(value = "商家")
	@Column(name = "upper_shelf")
		 private String upperShelf; //商家

	@ApiModelProperty(value = "商品名称")
	@Column(name = "name")
		 private String name;//商品名称

	@ApiModelProperty(value = "创建时间")
	@Column(name = "createtime")
		 private Timestamp  createtime;  //创建时间

	@ApiModelProperty(value = "修改时间")
	@Column(name = "modifytime")
		 private Timestamp modifytime;   //修改时间

	@ApiModelProperty(value = "分类")
	@Column(name = "category")
		 private String category;//分类
	@Column(name = "sub_category")
	private String subCategory;

	@ApiModelProperty(value = "国家")
	@Column(name = "country")
		 private String country;//国家

	@ApiModelProperty(value = "用户名")
	@Column(name = "user_name")
		 private String userName;//用户名

	@ApiModelProperty(value = "缩略图")
	@Column(name = "thumbnail_image")
	private String thumbnailImage;

	@ApiModelProperty(value = "原图")
	@Column(name = "original_image")
	private String originalImage;

	@ApiModelProperty(value = "原图")
	@Column(name = "shop_id")
	private Long shopId;

	@ApiModelProperty(value = "置顶")
	@Column(name = "top")
	private Integer top=0;
	@Column(name = "previous_price")
	private Long previousPrice;
	@Column(name = "send_address")
	private String sendAddress;


}
