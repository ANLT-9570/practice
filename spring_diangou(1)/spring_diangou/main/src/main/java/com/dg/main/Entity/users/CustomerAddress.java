package com.dg.main.Entity.users;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

//CustomerAddress
@Table(name = "customer_address")
@Entity
public class CustomerAddress implements Serializable{
	/**
	 * 
	 */

	private static final long serialVersionUID = -5189324869248184361L;

	@Column(name = "user_id")
	private Long userId;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "id",example = "1")
	@Column(name = "id")
	private Long id;

	@ApiModelProperty(value = "详细地址")
	@Column(name = "address")
	private String address ;  //详细地址
	@Column(name = "city")
	private String city;
	@Column(name = "province")
	private String province;
	@Column(name = "is_default")
	private Integer isDefault=0;
	@ApiModelProperty(value = "电话")
	@Column(name = "phone")
	private String phone;         //电话



	@ApiModelProperty(value = "邮编")
	@Column(name = "zip_code")
	private String zipCode;  //邮编

	@ApiModelProperty(value = "收货人")
	@Column(name = "consignee")
	private String consignee;    //收货人

	public CustomerAddress(Long userId, String address, String city, String province, Integer isDefault, String phone, String zipCode, String consignee, String name) {
		this.userId = userId;
		this.address = address;
		this.city = city;
		this.province = province;
		this.isDefault = isDefault;
		this.phone = phone;
		this.zipCode = zipCode;
		this.consignee = consignee;
		this.name = name;
	}

	public CustomerAddress() {
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}



	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ApiModelProperty(value = "名称")
	@Column(name = "name")
	private String name;


	
}
