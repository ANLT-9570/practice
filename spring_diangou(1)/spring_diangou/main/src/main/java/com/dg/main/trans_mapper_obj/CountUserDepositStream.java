package com.dg.main.trans_mapper_obj;

import org.omg.CORBA.INTERNAL;

public class CountUserDepositStream {
    private Integer type;
    private Long money;

    public CountUserDepositStream() {
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "CountUserDepositStream{" +
                "type=" + type +
                ", money=" + money +
                '}';
    }
}
