package com.dg.main.Entity.orders;

import javax.persistence.*;
import java.lang.reflect.AnnotatedArrayType;

@Entity
@Table(name = "user_third_platfrom_info")
public class UserThirdPlatformInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "zhifubao_auth_token")
    private String zhifubaoAuthToken;
    @Column(name = "zhifubao_refresht_token")
    private String zhifubaoRefreshToken;
    @Column(name = "zhifubao_auth_app_id")
    private String zhifubaoAuthAppId;
    @Column(name = "zhifubao_expires_in")
    private String zhifubaoExpiresIn;
    @Column(name = "zhifubao_reexpires_in")
    private String zhifubaoReexpirsIn;

    public String getZhifubaoRefreshToken() {
        return zhifubaoRefreshToken;
    }

    public void setZhifubaoRefreshToken(String zhifubaoRefreshToken) {
        this.zhifubaoRefreshToken = zhifubaoRefreshToken;
    }

    public String getZhifubaoAuthAppId() {
        return zhifubaoAuthAppId;
    }

    public void setZhifubaoAuthAppId(String zhifubaoAuthAppId) {
        this.zhifubaoAuthAppId = zhifubaoAuthAppId;
    }

    public String getZhifubaoExpiresIn() {
        return zhifubaoExpiresIn;
    }

    public void setZhifubaoExpiresIn(String zhifubaoExpiresIn) {
        this.zhifubaoExpiresIn = zhifubaoExpiresIn;
    }

    public String getZhifubaoReexpirsIn() {
        return zhifubaoReexpirsIn;
    }

    public void setZhifubaoReexpirsIn(String zhifubaoReexpirsIn) {
        this.zhifubaoReexpirsIn = zhifubaoReexpirsIn;
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

    public String getZhifubaoAuthToken() {
        return zhifubaoAuthToken;
    }

    public void setZhifubaoAuthToken(String zhifubaoAuthToken) {
        this.zhifubaoAuthToken = zhifubaoAuthToken;
    }

    public UserThirdPlatformInfo() {
    }
}
