package com.dg.main.Entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "sys_menu")
@Entity
public class Menu implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7070395572233373638L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;//id

	@ApiModelProperty(value = "菜单编号")
	@Column(name = "code")
	private String code;//菜单编号

	@ApiModelProperty(value = "菜单父编号")
	@Column(name = "pcode")
	private String pcode;//菜单父编号

	@ApiModelProperty(value = "当前菜单的所有父菜单编号")
	@Column(name = "pcodes")
	private String pcodes;//当前菜单的所有父菜单编号

	@ApiModelProperty(value = "菜单名称")
	@Column(name = "name")
	private String name;//菜单名称

	@ApiModelProperty(value = "图标")
	@Column(name = "icon")
	private String icon;//图标@Column(name = "mobile_use_id")

	@ApiModelProperty(value = "url地址")
	@Column(name = "url")
	private String url;//url地址

	@ApiModelProperty(value = "菜单排序号")
	@Column(name = "num")
	private String num;//菜单排序号

	@ApiModelProperty(value = "是否是菜单（1：是    0：不是)")
	@Column(name = "ismenu")
	private String ismenu;//是否是菜单（1：是    0：不是)

	@ApiModelProperty(value = "备注")
	@Column(name = "tips")
	private String tips;//备注@Column(name = "mobile_use_id")

	@ApiModelProperty(value = "菜单层级")
	@Column(name = "levels")
	private String levels;//菜单层级
//	@Column(name = "childrens")

	@ApiModelProperty(value = "子节点")
	@OneToMany
	@JoinColumn(name = "childrens")
	private List<Menu> childrens;
	
	public List<Menu> getChildrens() {
		return childrens;
	}
	public void setChildrens(List<Menu> childrens) {
		this.childrens = childrens;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLevels() {
		return levels;
	}
	public void setLevels(String levels) {
		this.levels = levels;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public String getPcodes() {
		return pcodes;
	}
	public void setPcodes(String pcodes) {
		this.pcodes = pcodes;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getIsmenu() {
		return ismenu;
	}
	public void setIsmenu(String ismenu) {
		this.ismenu = ismenu;
	}
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
	
	
	
}
