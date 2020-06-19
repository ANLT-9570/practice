package com.dg.main.Entity.users;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_real_name")
@Data
@ToString
@NoArgsConstructor
public class UserRealName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "id_car")
    private String IDcar;
    @Column(name = "real_name")
    private String realName;
    @Column(name = "create_time")
    private Date createTime=new Date();
    @Column(name = "modify_time")
    private Date modifyTime=new Date();
    @Column(name = "opp_img")
    private String oppImg;
    @Column(name = "neg_img")
    private String negImg;
    @Column(name = "selfie_img")
    private String selfieImg;
    @Column(name = "sex")
    private Integer sex;
    @Column(name = "phone")
    private String phone;
    private Integer status=0;//1.已认证，//0 未认证//2.审核未通过//3. 审核中
    @Column(name = "is_passed")
    private Integer isPassed=0;
    @Column(name = "reason")
    private String reason="";
    @Column(name = "email")
    private String email="";
    @Column(name = "birth_day")
    private String birthDay;
    @Column(name = "is_company")
    private Integer isCompany=0;
    @Column(name = "company_code")
    private String companyCode="";
    @Column(name = "company_license_img")
    private String companyLicenseImg="";

}
