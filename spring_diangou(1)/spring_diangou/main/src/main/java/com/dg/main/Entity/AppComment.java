package com.dg.main.Entity;

import java.io.Serializable;
import java.sql.Timestamp;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Table(name = "app_comment")
@Entity
public class AppComment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 784196197779512315L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
		private Long CommentId;
	@Column(name = "text")
		private String text;
	@Column(name = "mobile_user_id")
		private Long  mobileUserId;
	@Column(name = "specifications_id")
		private Long SpecificationsId;
	@Column(name = "image")
		private String image;
	@Column(name = "good")
		private Long  good;
	@Column(name = "bad")
		private Long  bad;
	@Column(name = "create_time")
		private Timestamp createTime;
	@Column(name = "reply_id")
		private Long  replyId;
	@Column(name = "stats")
		private int stats;
	@Column(name = "name")
		private String name;
	@Column(name = "time")
		private Timestamp time;
	@Column(name = "reply")
		private String reply;
	@Column(name = "replyname")
		private String replyname;
		
		
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
		public Long getGood() {
			return good;
		}
		public void setGood(Long good) {
			this.good = good;
		}
		public Long getBad() {
			return bad;
		}
		public void setBad(Long bad) {
			this.bad = bad;
		}
		public Timestamp getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Timestamp createTime) {
			this.createTime = createTime;
		}
		public Long getReplyId() {
			return replyId;
		}
		public void setReplyId(Long replyId) {
			this.replyId = replyId;
		}
		public int getStats() {
			return stats;
		}
		public void setStats(int stats) {
			this.stats = stats;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Timestamp getTime() {
			return time;
		}
		public void setTime(Timestamp time) {
			this.time = time;
		}
		public String getReply() {
			return reply;
		}
		public void setReply(String reply) {
			this.reply = reply;
		}
		public String getReplyname() {
			return replyname;
		}
		public void setReplyname(String replyname) {
			this.replyname = replyname;
		}
		
		
		


}
