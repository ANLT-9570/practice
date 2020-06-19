package com.dg.main.Entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "sys_user")
@Entity
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3586204355446418689L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;//用户id

	@ApiModelProperty(value = "账号")
	@Column(name = "account")
	private String account;//账号

	@ApiModelProperty(value = "密码")
	@Column(name = "password")
	private String password;//密码

	@ApiModelProperty(value = "用户名")
	@Column(name = "name")
	private String name;//用户名

	@ApiModelProperty(value = "性别")
	@Column(name = "sex")
	private String sex;//性别

	@ApiModelProperty(value = "邮箱")
	@Column(name = "email")
	private String email;//邮箱

	@ApiModelProperty(value = "电话")
	@Column(name = "phone")
	private String phone;//电话

	@ApiModelProperty(value = "角色id")
	@Column(name = "roleid")
	private String roleid;//角色id

	@ApiModelProperty(value = "状态")
	@Column(name = "status")
	private String status;//状态

	@ApiModelProperty(value = "创建时间")
	@Column(name = "createtime")
	private String createtime;//创建时间

	@ApiModelProperty(value = "ip地址")
	@Column(name = "i_p")
	private String IP; //ip地址

	@ApiModelProperty(value = "mac地址")
	@Column(name = "mac")
	private String  mac; //mac地址

	@ApiModelProperty(value = "角色",example = "1")
	@Column(name = "role")
	//	private String  phone; //电话
	private  int role;  //角色

	@ApiModelProperty(value = "令牌")
	@Column(name = "token")
	private  String token;  //令牌

	@ApiModelProperty(value = "简介")
	@Column(name = "introduction")
	private  String introduction; //简介

	@ApiModelProperty(value = "头像")
	@Column(name = "image")
	private  String  image; //头像

	@ApiModelProperty(value = "排名",example = "1")
	@Column(name = "sort")
	private Long sort;
	//private String name;  //姓名
//	@DateTimeFormat(pattern="yyyy-MM-dd")
//	private  Timestamp createtime;  //创建时间
	@Column(name = "modifytime")
	private String modifytime;   //结束时间

	@ApiModelProperty(value = "isShop",example = "1")
	@Column(name = "is_shop")
	private Integer isShop=2;

	public String getModifytime() {
		return modifytime;
	}

	public void setModifytime(String modifytime) {
		this.modifytime = modifytime;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String IP) {
		this.IP = IP;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}

	public Integer getIsShop() {
		return isShop;
	}

	public void setIsShop(Integer isShop) {
		this.isShop = isShop;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", password=" + password + ", name=" + name + ", sex=" + sex
				+ ", email=" + email + ", phone=" + phone + ", roleid=" + roleid + ", status=" + status
				+ ", createtime=" + createtime + "]";
	}
	
}
