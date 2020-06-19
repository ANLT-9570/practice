package com.dg.main.dto;

import org.apache.commons.compress.utils.Lists;

import java.util.List;

public class UserCouponsDao {
    private Long id;
    private List<UserCouponsItemDao> items= Lists.newArrayList();
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

    public List<UserCouponsItemDao> getItems() {
        return items;
    }

    public void setItems(List<UserCouponsItemDao> items) {
        this.items = items;
    }
}
