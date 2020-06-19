package com.dg.main.Entity;

import java.io.Serializable;
import java.sql.Timestamp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;


@Table(name = "administrator")
@Entity
public class Administrator implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8557664654260140345L;

	/**
	 * 管理员
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ApiModelProperty(value = "用户姓名")
	@Column(name = "name")
	private String name; //用户姓名

	@ApiModelProperty(value = "用户密码")
	@Column(name = "password")
	private String password; //用户密码

	@ApiModelProperty(value = "用户身份 1,为超级管理员、2、为厂商 ",example = "1")
	@Column(name = "role")
	private Long role; //用户身份 1,为超级管理员、2、为厂商

	@ApiModelProperty(value = "帐号创建时间")
	@Column(name = "createtime")
	private Timestamp createtime; //帐号创建时间

	@ApiModelProperty(value = "账号修改时间")
	@Column(name = "modifytime")
	private Timestamp modifytime; //账号修改时间

	@ApiModelProperty(value = "1、激活；2、冻结",example = "1")
	@Column(name = "status")
	private  int status;//1、激活；2、冻结


	
	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getRole() {
		return role;
	}

	public void setRole(Long role) {
		this.role = role;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public Timestamp getModifytime() {
		return modifytime;
	}

	public void setModifytime(Timestamp modifytime) {
		this.modifytime = modifytime;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	
	

}
