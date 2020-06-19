package com.dg.main.param.orders;

import java.util.List;

public class CreateOrderbyOrderItemsCodeParam {
   private List<String> codes;
   private String mark;

    public List<String> getCodes() {
        return codes;
    }

    public void setCodes(List<String> codes) {
        this.codes = codes;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
