package com.dg.main.Entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "feedback")
@Entity
public class Feedback implements Serializable{
	public Long getFeedbackId() {
		return FeedbackId;
	}

	public void setFeedbackId(Long feedbackId) {
		FeedbackId = feedbackId;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4419116721831482139L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "feedback_id")
	private Long FeedbackId ;

	@ApiModelProperty(value = "意见")
	@Column(name = "text")
	private String text; // 意见

	@ApiModelProperty(value = "用户id",example = "1")
	@Column(name = "mobile_use_id")
	private Long mobileUseId;  //用户id

	@ApiModelProperty(value = "名称")
	@Column(name = "name")
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
