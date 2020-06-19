package com.dg.main.vo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SpecificationsVo {
    private Long 	id;
    private Long userId; //商家id
    private String  describe;//描述
    private String img; //图片
    private Long types; // 类型（1，日常用品 2，家电 3，饮食）
    private Long Price; //单价
    private Long discountPrice; //折扣价
    private String brand; //品牌
    private Long number ;// 数量
    private String mydescribe;
    private String size; //尺寸//尺寸   规格名称_尺寸(单位)_库存_原价_优惠价_重量(单位)_颜色_图片路径。
    private String salesService;//售后服务
    private String colour; //颜色
    private Long weight; //重量
    private Integer status;//1上架，2下架，3删除
    private String upperShelf; //商家
    private String shopName;//商品名称
    private Timestamp createtime;  //创建时间
    private Timestamp modifytime;   //修改时间
    private String category;//分类
    private String country;//国家
    private String userName;//用户名
    String token;
    String registerID;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRegisterID() {
        return registerID;
    }

    public void setRegisterID(String registerID) {
        this.registerID = registerID;
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

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Long getTypes() {
        return types;
    }

    public void setTypes(Long types) {
        this.types = types;
    }

    public Long getPrice() {
        return Price;
    }

    public void setPrice(Long price) {
        Price = price;
    }

    public Long getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Long discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getMydescribe() {
        return mydescribe;
    }

    public void setMydescribe(String mydescribe) {
        this.mydescribe = mydescribe;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSalesService() {
        return salesService;
    }

    public void setSalesService(String salesService) {
        this.salesService = salesService;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUpperShelf() {
        return upperShelf;
    }

    public void setUpperShelf(String upperShelf) {
        this.upperShelf = upperShelf;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public Timestamp getModifytime() {
        return modifytime;
    }

    public void setModifytime(Timestamp modifytime) {
        this.modifytime = modifytime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(String thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }

    public String getOriginalImage() {
        return originalImage;
    }

    public void setOriginalImage(String originalImage) {
        this.originalImage = originalImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getSalesTotal() {
        return salesTotal;
    }

    public void setSalesTotal(Integer salesTotal) {
        this.salesTotal = salesTotal;
    }

    private String thumbnailImage;
    private String originalImage;
    private String name;
    private Long shopId;
    private String storeName;
    private Integer salesTotal;

}
