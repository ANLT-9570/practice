package com.dg.main.vo;

import com.dg.main.Entity.orders.OrderItems;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

public class WaitForReceivingVo {
    private Long id;
    private String shopsName;
    private List<OrderItems> items = Lists.newArrayList();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopsName() {
        return shopsName;
    }

    public void setShopsName(String shopsName) {
        this.shopsName = shopsName;
    }

    public List<OrderItems> getItems() {
        return items;
    }

    public void setItems(List<OrderItems> items) {
        this.items = items;
    }
}
