package com.dg.main.Entity.logs;

import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "callback_failure_log")
public class CallbackFailureLog   implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "types")
    private Integer types;
    @Column(name = "message")
    private String message;
    @Column(name = "create_time")
    private Date createTime=new Date();
    @Column(name = "code")
    private String code;
    @Column(name = "locations")
    private String locations;
    @Column(name = "third_code")
    private String thirdCode;

    public String getThirdCode() {
        return thirdCode;
    }

    public void setThirdCode(String thirdCode) {
        this.thirdCode = thirdCode;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return "CallbackFailureLog{" +
                "id=" + id +
                ", types=" + types +
                ", message='" + message + '\'' +
                ", createTime=" + createTime +
                ", code='" + code + '\'' +
                '}';
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CallbackFailureLog() {
    }
}
