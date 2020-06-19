package com.dg.main.Entity.es;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Document(indexName = "personinfo")
public class PersonINFO {

    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String token;

    @Field(type = FieldType.Text)
    private String account;

    @Field(type = FieldType.Text)
    private Long mobileUseId;

    @Field(type = FieldType.Keyword)
    private String shopName; //店名
//    private Long	mobileUseId; //商家
    @Field(type = FieldType.Keyword)
    private Long sex; //性别3

    @Field(type = FieldType.Keyword)
    private String phone; //手机号码
    @Field(type = FieldType.Keyword)
    private String 	verificationCode;//验证码
    @Field(type = FieldType.Keyword)
    private String 	IDcar; //身份证
    @Field(type = FieldType.Keyword)
    private String photo; //相片
    @Field(type = FieldType.Keyword)
    private String 	BankAmericard; //银行卡
    @Field(type = FieldType.Keyword)
    private Long status; //状态 1未通过 2通过
    @Field(type = FieldType.Text)
    private String realityName;//真实姓名
    @Field(type = FieldType.Keyword)
    private  String introduction; //简介
    @Field(type = FieldType.Keyword)
    private Timestamp createtime;  //创建时间
    @Field(type = FieldType.Keyword)
    private Timestamp  modifytime ;  //修改时间
    @Field(type = FieldType.Keyword)
    private int salesVolume;//销售量
    @Field(type = FieldType.Keyword)
    private int BrowsingVolume;//浏览量
    @Field(type = FieldType.Keyword)
    private int sort;//排名
    @Field(type = FieldType.Keyword)
    private int todayOrder;//今日订单
    @Field(type = FieldType.Keyword)
    private Long todayIncome;//今日收入
    @Field(type = FieldType.Keyword)
    private String name;
    @Field(type = FieldType.Keyword)
    private String shopPictures;//店铺图片
    @Field(type = FieldType.Keyword)
    private String shopIntroduction; //店铺描述
    @Field(type = FieldType.Keyword)
    private BigDecimal capitalVolume;//资金量 （拿来排行的）
    @Field(type = FieldType.Keyword)
    private String shippingAddress; //发货地址
    @Field(type = FieldType.Keyword)
    private Long redPackMoney;//发的红包钱
    @Field(type = FieldType.Keyword)
    private String img;//店铺图片
    @Field(type = FieldType.Keyword)
    private Long leftMoney;
    @Field(type = FieldType.Keyword)
    private Long leftww;
    @Field(type = FieldType.Keyword)
    private String indexImage;
    @Field(type = FieldType.Keyword)
    private String info;

    @Field(type = FieldType.Keyword)
    private  String SpecificationsId; // 规格id
    @Field(type = FieldType.Keyword)
    private  int pageviews;//浏览量

    @Field(type = FieldType.Keyword)
    private String prefectureLevelCity;//地级市
    @Field(type = FieldType.Keyword)
    private String longitude;//经度
    @Field(type = FieldType.Keyword)
    private String latitude;//纬度

    public PersonINFO() {

    }

    public PersonINFO(Long id, String token, String account, Long mobileUseId, String shopName, Long sex, String phone, String verificationCode, String IDcar, String photo, String bankAmericard, Long status, String realityName, String introduction, Timestamp createtime, Timestamp modifytime, int salesVolume, int browsingVolume, int sort, int todayOrder, Long todayIncome, String name, String shopPictures, String shopIntroduction, BigDecimal capitalVolume, String shippingAddress, Long redPackMoney, String img, Long leftMoney, Long leftww, String indexImage, String info, String specificationsId, int pageviews, String prefectureLevelCity, String longitude, String latitude) {
        this.id = id;
        this.token = token;
        this.account = account;
        this.mobileUseId = mobileUseId;
        this.shopName = shopName;
        this.sex = sex;
        this.phone = phone;
        this.verificationCode = verificationCode;
        this.IDcar = IDcar;
        this.photo = photo;
        this.BankAmericard = bankAmericard;
        this.status = status;
        this.realityName = realityName;
        this.introduction = introduction;
        this.createtime = createtime;
        this.modifytime = modifytime;
        this.salesVolume = salesVolume;
        this.BrowsingVolume = browsingVolume;
        this.sort = sort;
        this.todayOrder = todayOrder;
        this.todayIncome = todayIncome;
        this.name = name;
        this.shopPictures = shopPictures;
        this.shopIntroduction = shopIntroduction;
        this.capitalVolume = capitalVolume;
        this.shippingAddress = shippingAddress;
        this.redPackMoney = redPackMoney;
        this.img = img;
        this.leftMoney = leftMoney;
        this.leftww = leftww;
        this.indexImage = indexImage;
        this.info = info;
        this.SpecificationsId = specificationsId;
        this.pageviews = pageviews;
        this.prefectureLevelCity = prefectureLevelCity;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
