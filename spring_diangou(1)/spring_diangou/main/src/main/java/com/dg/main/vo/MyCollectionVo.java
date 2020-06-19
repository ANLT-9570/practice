package com.dg.main.vo;

import lombok.Data;
import org.apache.commons.compress.utils.Lists;

import javax.persistence.Entity;
import java.util.List;

@Data
public class MyCollectionVo{

	private Long id;

	private Long 	userId;//用户id

	private Long 	shopId;//商铺id
	private Long specificationsId;//(1,商品)

	private Integer status; //（1，取消关注，2关注）

	private String name;

	private String shopName;//商品名称
	private String introduction;//商品描述
	private Long price;
	private String image;//商品图片

}
