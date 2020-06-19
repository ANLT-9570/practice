package com.dg.main.vo;

import com.dg.main.Entity.shop.Specifications;
import lombok.Data;
import org.apache.commons.compress.utils.Lists;

import java.util.Date;
import java.util.List;

@Data
public class ShopsVo {
    private Long id;
    private Long userId;
    private String brandImg;
    private String displayImg;
    private String name;
    private String userName;
    private String descriptions;
    private String registerID;
    private String chatToken;

    public String getRegisterID() {
        return registerID;
    }

    public void setRegisterID(String registerID) {
        this.registerID = registerID;
    }

    public String getChatToken() {
        return chatToken;
    }

    public void setChatToken(String chatToken) {
        this.chatToken = chatToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
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

    public String getBrandImg() {
        return brandImg;
    }

    public void setBrandImg(String brandImg) {
        this.brandImg = brandImg;
    }

    public String getDisplayImg() {
        return displayImg;
    }

    public void setDisplayImg(String displayImg) {
        this.displayImg = displayImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getBrowseNumber() {
        return browseNumber;
    }

    public void setBrowseNumber(Long browseNumber) {
        this.browseNumber = browseNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getIncome() {
        return income;
    }

    public void setIncome(Long income) {
        this.income = income;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Integer getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Integer totalSales) {
        this.totalSales = totalSales;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsSendRedPack() {
        return isSendRedPack;
    }

    public void setIsSendRedPack(Integer isSendRedPack) {
        this.isSendRedPack = isSendRedPack;
    }

    public List<Specifications> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<Specifications> specifications) {
        this.specifications = specifications;
    }

    public List<ShopsItemVo> getItemVos() {
        return itemVos;
    }

    public void setItemVos(List<ShopsItemVo> itemVos) {
        this.itemVos = itemVos;
    }

    private String description;
    private Long browseNumber;
    private Date createTime=new Date();
    private Long income;
    private String city;
    private String area;
    private Integer categoryId;
    private String province;
    private String lat;
    private String lon;
    private String addr;
    private Long money;
    private Integer totalSales;
    private Integer status;
    private Integer isSendRedPack;
    List<Specifications> specifications = Lists.newArrayList();
    List<ShopsItemVo> itemVos = Lists.newArrayList();

}
