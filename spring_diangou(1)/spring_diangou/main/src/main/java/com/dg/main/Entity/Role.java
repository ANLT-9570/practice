package com.dg.main.Entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "sys_role")
@Entity
public class Role implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7142898581764489237L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;//角色id

	@ApiModelProperty(value = "序号")
	@Column(name = "num")
	private String num;//序号

	@ApiModelProperty(value = "角色名称")
	@Column(name = "name")
	private String name;//角色名称

	@ApiModelProperty(value = "父角色id")
	@Column(name = "pid")
	private String pid;//父角色id

	@ApiModelProperty(value = "角色描述")
	@Column(name = "description")
	private String description;//角色描述

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
