package com.dg.main.dto.orders;

import javax.persistence.Column;
import java.util.List;

public class OrderCommentDto {

    private Long specificationId;

    private Long userId;

    private Long sellerId;

    private Integer ranks=0;

    private String comment;

    private List<String> commentOriginalImg;

    private List<String> commentThumbImg;
    private String itemCode;
    private String username;

    public Long getSpecificationId() {
        return specificationId;
    }

    public void setSpecificationId(Long specificationId) {
        this.specificationId = specificationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getRanks() {
        return ranks;
    }

    public void setRanks(Integer ranks) {
        this.ranks = ranks;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<String> getCommentOriginalImg() {
        return commentOriginalImg;
    }

    public void setCommentOriginalImg(List<String> commentOriginalImg) {
        this.commentOriginalImg = commentOriginalImg;
    }

    public List<String> getCommentThumbImg() {
        return commentThumbImg;
    }

    public void setCommentThumbImg(List<String> commentThumbImg) {
        this.commentThumbImg = commentThumbImg;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
