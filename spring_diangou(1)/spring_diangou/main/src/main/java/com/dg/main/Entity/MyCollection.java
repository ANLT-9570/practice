package com.dg.main.Entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

//收藏
@Table(name = "my_collection")
@Entity
@Data
public class MyCollection implements Serializable{
	private static final long serialVersionUID = -8509304600318084294L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ApiModelProperty(value = "用户id",example = "1")
	@Column(name = "user_id")
	private Long 	userId;//用户id

	@ApiModelProperty(value = "商铺id",example = "1")
	@Column(name = "shop_id")
	private Long 	shopId;//商铺id


	@ApiModelProperty(value = "商品",example = "1")
	@Column(name = "specifications_id")
	private Long specificationsId;//(1,商品)

	@ApiModelProperty(value = "（1，取消关注，2关注）",example = "1")
	@Column(name = "status")
	private Integer status; //（1，取消关注，2关注）

	@ApiModelProperty(value = "名称")
	@Column(name = "name")
	private String name;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Long getSpecificationsId() {
		return specificationsId;
	}

	public void setSpecificationsId(Long specificationsId) {
		this.specificationsId = specificationsId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
