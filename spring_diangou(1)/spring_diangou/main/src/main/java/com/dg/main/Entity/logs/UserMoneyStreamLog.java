package com.dg.main.Entity.logs;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
//用户现金流记录
@Entity
@Table(name = "user_money_stream_log")
public class UserMoneyStreamLog  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;//用户id
    @Column(name = "json_of_previous_user_balance")
    private String jsonOfPreviousUserBalance;//用户上期账单余额
    @Column(name = "create_time")
    private Date createTime=new Date();//创建时间
    @Column(name ="json_of_current_user_balance" )
    private String jsonOfCurrentUserBalance;//用户当前余额
    @Column(name = "involved_class_name")
    private String involvedClassName;
    @Column(name = "involved_method_name")
    private String involvedMethodName;
    @Column(name = "exe_class_name")
    private String exeClassname;
    @Column(name = "exe_method_name")
    private String exeMethodName;
    @Column(name = "types")
    private Integer types;
    @Column(name = "code")
    private String code;


    public String getInvolvedClassName() {
        return involvedClassName;
    }

    public void setInvolvedClassName(String involvedClassName) {
        this.involvedClassName = involvedClassName;
    }

    public String getInvolvedMethodName() {
        return involvedMethodName;
    }

    public void setInvolvedMethodName(String involvedMethodName) {
        this.involvedMethodName = involvedMethodName;
    }

    public String getExeClassname() {
        return exeClassname;
    }

    public void setExeClassname(String exeClassname) {
        this.exeClassname = exeClassname;
    }

    public String getExeMethodName() {
        return exeMethodName;
    }

    public void setExeMethodName(String exeMethodName) {
        this.exeMethodName = exeMethodName;
    }

    public Integer getTypes() {
        return types;
    }

    public void setTypes(Integer types) {
        this.types = types;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "UserMoneyStreamLog{" +
                "id=" + id +
                ", userId=" + userId +
                ", jsonOfPreviousUserBalance='" + jsonOfPreviousUserBalance + '\'' +
                ", createTime=" + createTime +
                ", jsonOfCurrentUserBalance='" + jsonOfCurrentUserBalance + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getJsonOfPreviousUserBalance() {
        return jsonOfPreviousUserBalance;
    }

    public void setJsonOfPreviousUserBalance(String jsonOfPreviousUserBalance) {
        this.jsonOfPreviousUserBalance = jsonOfPreviousUserBalance;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getJsonOfCurrentUserBalance() {
        return jsonOfCurrentUserBalance;
    }

    public void setJsonOfCurrentUserBalance(String jsonOfCurrentUserBalance) {
        this.jsonOfCurrentUserBalance = jsonOfCurrentUserBalance;
    }

    public UserMoneyStreamLog() {
    }
}
