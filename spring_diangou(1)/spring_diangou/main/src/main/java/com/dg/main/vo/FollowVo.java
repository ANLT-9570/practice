package com.dg.main.vo;

import com.dg.main.Entity.Follow;
import lombok.Data;

import java.io.Serializable;

//关注
@Data
public class FollowVo implements Serializable{

	private static final long serialVersionUID = 518417451182165131L;
	private Long id ;

	private Long SpecificationsId; //关注

	private Long shopId;// 商铺Id@Column(name = "text")

	private Long userId;//用户ID

	private Long status; //（1，取消关注，2关注）

	private String name;

	private String image;
	private String shopName;
	
	

}
