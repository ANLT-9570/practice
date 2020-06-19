package com.dg.main.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@NoArgsConstructor
@ToString
@Data
public class MobileUserVo {
    private Long mobileUseId  ;

    private String IP;

    private String  mac;

    private String  phone;

    private  Integer role;

    private  String token;

    private  String introduction; //简介

    private  String  image;

    private Long sort;

    private String name;

    private Timestamp createtime;

    private  Timestamp modifytime;

    private Integer mark;
    private String chatToken;
    private String loginPassword;
    private String payPassword;
    private Integer sex;
    private String registerID;
    private Integer isCompanyEmployee=2;
    private String inviteCode;
}
