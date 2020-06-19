package com.yzl.utils.vo;

import java.util.List;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.yzl.utils.trees;

public class DistrictTreeVO {
	
	/***
	 * 节点Id
	 */
	private String id;
	/***
	 * 节点名称
	 */
	private String text;
	/***
	 * 树节点的状态   
	 */
	private String state = "closed"; //节点默认是关闭的
	/***
	 * 子节点
	 */
	private List<DistrictTreeVO> children;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		//如果是叶子节点，默认状态是打开 oepn
		if(CollectionUtils.isEmpty(children)){
			return "open";
		}
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<DistrictTreeVO> getChildren() {
		return children;
	}
	public void setChildren(List<DistrictTreeVO> children) {
		
		this.children = children;
		if(children!=null&&children.size()==1){
			state="open";//如果只有一个节点，则为打开状态
			id=children.get(0).id;
		}
	}
	@Override
	public String toString() {
		return "DistrictTreeVO [id=" + id + ", text=" + text + ", state=" + state + ", children=" + children + "]";
	}
	
	
	
}
