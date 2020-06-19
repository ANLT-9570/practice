package com.dg.main.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class PersonDto {
    private Long userId;
    private String  phone;
    private  String introduction; //简介
    private  String  avatarImg;
    private String nickName;
    private String chatToken;
    private Integer sex;
    private String realName;
    private Integer status;
    private Long accountId;
    private Integer zhifubaoAccountStatus;
    private Integer weixinAccountStatus;
    private String province;
    private String city;
    private String address ;  //详细地址
    private String sendPhone;         //电话
    private String consignee;
    private String zipCode;
    private Integer role;
    private Long money;
    private Long platformMoney;
    private Long shopId;
    private Integer isVerified=2;
    private String IMID;
    private String shopChatToken;
    private String shopIMID;
    private Long addressId;
    private String zhifubaoAccountName;
    private String zhifubaoAccount;
    private String weixinAccount;
    private String weixinAccountName;
  //  private String payPassword;
    private String inviteCode;
    private String inviteCodeImage;


}
