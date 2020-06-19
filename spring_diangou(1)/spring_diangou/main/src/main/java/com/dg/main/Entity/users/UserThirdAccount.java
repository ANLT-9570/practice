package com.dg.main.Entity.users;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.omg.CORBA.INTERNAL;
import org.omg.CORBA.NamedValue;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_third_account")
@Data
@ToString
@NoArgsConstructor
public class UserThirdAccount implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "zhifubao_payee_type")
    private String zhifubaoPayeeType="ALIPAY_LOGONID";
    @Column(name = "zhifubao_payee_account")
    private String zhifubaoPayeeAccount="";
    @Column(name = "zhifubao_payer_show_name")
    private String zhifubaoPayerShowName="";
    @Column(name = "zhifubao_payee_real_name")
    private String zhifubaoPayeeRealName="";
    @Column(name = "weixin_open_id")
    private String weixinOpenId="";
    @Column(name = "weinxin_real_name")
    private String weixinRealName="";

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

    public String getZhifubaoPayeeType() {
        return zhifubaoPayeeType;
    }

    public void setZhifubaoPayeeType(String zhifubaoPayeeType) {
        this.zhifubaoPayeeType = zhifubaoPayeeType;
    }

    public String getZhifubaoPayeeAccount() {
        return zhifubaoPayeeAccount;
    }

    public void setZhifubaoPayeeAccount(String zhifubaoPayeeAccount) {
        this.zhifubaoPayeeAccount = zhifubaoPayeeAccount;
    }

    public String getZhifubaoPayerShowName() {
        return zhifubaoPayerShowName;
    }

    public void setZhifubaoPayerShowName(String zhifubaoPayerShowName) {
        this.zhifubaoPayerShowName = zhifubaoPayerShowName;
    }

    public String getZhifubaoPayeeRealName() {
        return zhifubaoPayeeRealName;
    }

    public void setZhifubaoPayeeRealName(String zhifubaoPayeeRealName) {
        this.zhifubaoPayeeRealName = zhifubaoPayeeRealName;
    }

    public String getWeixinOpenId() {
        return weixinOpenId;
    }

    public void setWeixinOpenId(String weixinOpenId) {
        this.weixinOpenId = weixinOpenId;
    }

    public String getWeixinRealName() {
        return weixinRealName;
    }

    public void setWeixinRealName(String weixinRealName) {
        this.weixinRealName = weixinRealName;
    }
}
