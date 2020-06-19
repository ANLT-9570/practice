package com.dg.main.Entity.message;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "user_order_Message")
@Entity
public class UserOrderMessage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "message")
    private String message;
    @Column(name = "create_time")
    private Date createTime=new Date();
    @Column(name = "is_read")
    private Integer isRead=2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public UserOrderMessage() {
    }
}
