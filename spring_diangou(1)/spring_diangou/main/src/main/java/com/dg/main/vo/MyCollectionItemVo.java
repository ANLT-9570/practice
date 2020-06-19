package com.dg.main.vo;

import lombok.Data;

@Data
public class MyCollectionItemVo {
	private String shopName;//商品名称
	private String introduction;//商品描述
	private Long Price;
	private String image;//商品图片
}
