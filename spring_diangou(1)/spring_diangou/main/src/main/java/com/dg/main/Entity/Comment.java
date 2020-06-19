package com.dg.main.Entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

//评论
@Table(name = "comment")
@Entity
public class Comment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 722651936095083421L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
	private Long CommentId;//评论id

	@ApiModelProperty(value = "意见")
	@Column(name = "text")
	private String text;//意见

	@ApiModelProperty(value = "户id",example = "1")
	@Column(name = "mobile_use_id")
	private Long mobileUseId;//用户id

	@ApiModelProperty(value = "用户名")
	@Column(name = "name")
	private String name;//用户名

	@ApiModelProperty(value = "商品id",example = "1")
	@Column(name = "specifications_id")
	private Long SpecificationsId;//商品id

	@ApiModelProperty(value = "图片")
	@Column(name = "image")
	private String image;
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getSpecificationsId() {
		return SpecificationsId;
	}
	public void setSpecificationsId(Long specificationsId) {
		SpecificationsId = specificationsId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Long getCommentId() {
		return CommentId;
	}

	public void setCommentId(Long commentId) {
		CommentId = commentId;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Long getMobileUseId() {
		return mobileUseId;
	}
	public void setMobileUseId(Long mobileUseId) {
		this.mobileUseId = mobileUseId;
	}
	
	
}
