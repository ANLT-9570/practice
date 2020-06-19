package com.dg.main.Entity.message;

import javax.persistence.*;
import java.util.Date;
//系统消息
@Table(name = "system_message")
@Entity
public class SystemMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "message")
    private String message;
    @Column(name = "create_time")
    private Date createTime=new Date();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "SystemMessage{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public SystemMessage() {
    }
}
