package com.yzl.utils.vo;

import java.util.List;
import java.util.Map;

public class HighchartsResultVO {
	
	//城市名称
	private String name;
	//城市任务完成比例
	private Double y;
	//子节点name和  子节点的id对应
	private String drilldown;
	
	//子节点
	private DrilldownNode drilldownNode;
	
	public String getDrilldown() {
		return drilldown;
	}
	public void setDrilldown(String drilldown) {
		this.drilldown = drilldown;
	}



	
	public DrilldownNode getDrilldownNode() {
		return drilldownNode;
	}
	public void setDrilldownNode(DrilldownNode drilldownNode) {
		this.drilldownNode = drilldownNode;
	}
	public Double getY() {
		return y;
	}
	public void setY(Double y) {
		this.y = y;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "HighchartsResultVO [name=" + name + ", y=" + y + ", drilldown=" + drilldown + ", drilldownNode="
				+ drilldownNode + "]";
	}



	
	
	
}
