package com.dg.main.vo;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class RedPackVo{
	private Long userId;
	private Long platformMoney;//平台的钱
	private Long shopId;
	private String code;
	private Long version;
	private Timestamp modified=new Timestamp(new Date().getTime());
	private Timestamp createTime=new Timestamp(new Date().getTime());
	private Timestamp sendTime=null;

	private Integer isTaked=2;
	private Integer takeNumber=0;
	private Timestamp schedulerTime=null;
	private Integer userTaked=0;
	private String generatedNumbers;
	private Long leftMoney;
	private String shopName;

}
