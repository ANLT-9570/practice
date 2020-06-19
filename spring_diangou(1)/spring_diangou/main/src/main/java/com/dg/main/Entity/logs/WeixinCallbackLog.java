package com.dg.main.Entity.logs;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
//微信回调失败
@Entity
@Table(name = "weixin_callback_log")
public class WeixinCallbackLog   implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "types")
    private Integer types;
    @Column(name = "codes")
    private String codes;
    @Column(name = "reason")
    private String reason;
    @Column(name = "create_time")
    private Date createTime=new Date();
    @Column(name = "json")
    private String json;
    @Column(name = "is_success")
    private Integer isSuccess=2;

    public Integer getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Integer isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public WeixinCallbackLog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTypes() {
        return types;
    }

    public void setTypes(Integer types) {
        this.types = types;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
