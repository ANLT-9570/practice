package com.dg.main.Entity.users;

import java.io.Serializable;
import java.sql.Timestamp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

//鍟嗗
@Table(name = "mobile_user")
@Entity
@ToString
@NoArgsConstructor
@Data
public class MobileUser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7038844266108340574L;

	public Long getMobileUseId() {
		return mobileUseId;
	}

	public void setMobileUseId(Long mobileUseId) {
		this.mobileUseId = mobileUseId;
	}

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mobile_use_id")
	private Long mobileUseId  ;

	@ApiModelProperty(value = "IP")
	@Column(name = "i_p")
	private String IP;

	@ApiModelProperty(value = "mac")
	@Column(name = "mac")
	private String  mac;

	@ApiModelProperty(value = "电话")
	@Column(name = "phone")
	private String  phone;

	@ApiModelProperty(value = "角色",example = "1")
	@Column(name = "role")
	private  Integer role;

	@ApiModelProperty(value = "token")
	@Column(name = "token")
	private  String token;

	@ApiModelProperty(value = "简介")
	@Column(name = "introduction")
	private  String introduction; //简介

	@ApiModelProperty(value = "图片")
	@Column(name = "image")
	private  String  image;

	@ApiModelProperty(value = "排名",example = "1")
	@Column(name = "sort")
	private Long sort;

	@ApiModelProperty(value = "名称")
	@Column(name = "name")
	private String name;

	@ApiModelProperty(value = "创建时间")
	@Column(name = "createtime")
	private  Timestamp createtime;

	@ApiModelProperty(value = "修改时间")
	@Column(name = "modifytime")
	private  Timestamp modifytime;

	@ApiModelProperty(value = "标记",example = "1")
	@Column(name = "mark")
	private Integer mark;
	@Column(name = "chat_token")
	private String chatToken;
	@Column(name = "login_password")
	private String loginPassword;
	@Column(name = "pay_password")
	private String payPassword;
	private Integer sex;
	@Column(name = "register_id")
	private String registerID;
	@Column(name = "is_company_employee")
	private Integer isCompanyEmployee=2;
	@Column(name = "invite_code")
	private String inviteCode;
	@Column(name = "invite_code_image")
	private String inviteCodeImage;


}
