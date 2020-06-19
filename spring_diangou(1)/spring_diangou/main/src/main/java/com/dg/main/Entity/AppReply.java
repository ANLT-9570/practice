package com.dg.main.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "app_reply")
@Entity
public class AppReply implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3008433267489289048L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reply_id")
	private Long  replyId;
	@Column(name = "text")
	private String text;
	@Column(name = "mobile_user_id")
	private Long  mobileUserId;
	@Column(name = "specifications_id")
	private Long SpecificationsId;
	@Column(name = "image")
	private String image;
	@Column(name = "create_time")
	private Timestamp createTime;
	@Column(name = "name")
	private String name;
	@Column(name = "comment_id")
	private Long CommentId;

	public Long getReplyId() {
		return replyId;
	}
	public void setReplyId(Long replyId) {
		this.replyId = replyId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Long getMobileUserId() {
		return mobileUserId;
	}
	public void setMobileUserId(Long mobileUserId) {
		this.mobileUserId = mobileUserId;
	}
	public Long getSpecificationsId() {
		return SpecificationsId;
	}
	public void setSpecificationsId(Long specificationsId) {
		SpecificationsId = specificationsId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
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
	
}
