package com.yzl.utils.vo;

import java.util.List;

import com.yzl.pojo.YzlMenu;

public class TreeVO {

	
	private String id;
    /**
     * 父权限编号
     */
    private String pid;
    /**
     * 路径
     */
	private String url;
	/***
	 * 是否是菜单
	 */
	private String generatemenu;
	/**
	 * 图片
	 */
	private String icon;
	/***
	 * 名称
	 */
	private String name;
	/***
	 * 子节点
	 */
	List<YzlMenu> children;

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getGeneratemenu() {
		return generatemenu;
	}
	public void setGeneratemenu(String generatemenu) {
		this.generatemenu = generatemenu;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<YzlMenu> getChildren() {
		return children;
	}
	public void setChildren(List<YzlMenu> children) {
		this.children = children;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	
	
}
