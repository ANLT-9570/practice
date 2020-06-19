package com.dg.main.Entity.shop;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "size")
@Entity
@Data
@ToString
@NoArgsConstructor
public class Size implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4269990387444395443L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;////id
	@Column(name = "dimensions")
	private String dimensions;//尺寸@Column(name = "Sspecifications_id")
	@Column(name = "stock")
	private Integer stock;//库存
	@Column(name = "price")
	private Long price;//价格
	@Column(name = "discount_price")
	private Long DiscountPrice;//折后价
	@Column(name = "weight")
	private String weight;//体重
	@Column(name = "colour")
	private String colour;//颜色
	@Column(name = "img")
	private String img;//图片
	@Column(name = "specifications_id")
	private Long specificationsId;//规格表的Id
	@Column(name = "thumbnail_image")
	private String thumbnailImage;
	@Column(name = "previous_price")
	private Long previousPrice;

}
