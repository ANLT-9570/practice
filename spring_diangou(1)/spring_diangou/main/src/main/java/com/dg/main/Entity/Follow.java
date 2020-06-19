package com.dg.main.Entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

//关注
/**
 * @author Administrator
 *
 */
@Table(name = "follow")
@Entity
@Data
public class Follow implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4513775829812963521L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id ;

	@ApiModelProperty(value = "关注Id",example = "1")
	@Column(name = "specifications_id")
	private Long SpecificationsId; //关注

	@ApiModelProperty(value = "商铺Id",example = "1")
	@Column(name = "shop_id")
	private Long shopId;// 商铺Id@Column(name = "text")

	@ApiModelProperty(value = "用户ID",example = "1")
	@Column(name = "user_id")
	private Long userId;//用户ID

	@ApiModelProperty(value = "名称",example = "1")
	@Column(name = "status")
	private Long status; //（1，取消关注，2关注）

	@ApiModelProperty("名称")
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

	public Long getSpecificationsId() {
		return SpecificationsId;
	}

	public void setSpecificationsId(Long specificationsId) {
		SpecificationsId = specificationsId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
