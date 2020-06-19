package com.dg.main.dto.shop;

import com.dg.main.Entity.shop.ShopCarItems;
import org.apache.commons.compress.utils.Lists;

import javax.persistence.Column;
import java.util.List;

public class ShopCarDto {
    private Long id;

    private Long userId;
    private Long sellerId;

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    private Long shopId;
    private List<ShopCarItemsDao> items= Lists.newArrayList();
    private String shopName;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
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

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public List<ShopCarItemsDao> getItems() {
        return items;
    }

    public void setItems(List<ShopCarItemsDao> items) {
        this.items = items;
    }
}
