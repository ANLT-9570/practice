package com.dg.main.Entity;

public class HomePageState {
    Long todayNotShipTotal = 0l;
    Long todayRevenue = 0l;
    Long todayRefund=0l;
    Long countSendNumber = 0L;
    Long totalSendMoney = 0L;
    Long countTakeNumber = 0L;
    Long totalTakeMoney = 0L;
    Long totalRecords = 0L;

    public Long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public Long getTodayNotShipTotal() {
        return todayNotShipTotal;
    }

    public void setTodayNotShipTotal(Long todayNotShipTotal) {
        this.todayNotShipTotal = todayNotShipTotal;
    }

    public Long getTodayRevenue() {
        return todayRevenue;
    }

    public void setTodayRevenue(Long todayRevenue) {
        this.todayRevenue = todayRevenue;
    }

    public Long getTodayRefund() {
        return todayRefund;
    }

    public void setTodayRefund(Long todayRefund) {
        this.todayRefund = todayRefund;
    }

    public Long getCountSendNumber() {
        return countSendNumber;
    }

    public void setCountSendNumber(Long countSendNumber) {
        this.countSendNumber = countSendNumber;
    }

    public Long getTotalSendMoney() {
        return totalSendMoney;
    }

    public void setTotalSendMoney(Long totalSendMoney) {
        this.totalSendMoney = totalSendMoney;
    }

    public Long getCountTakeNumber() {
        return countTakeNumber;
    }

    public void setCountTakeNumber(Long countTakeNumber) {
        this.countTakeNumber = countTakeNumber;
    }

    public Long getTotalTakeMoney() {
        return totalTakeMoney;
    }

    public void setTotalTakeMoney(Long totalTakeMoney) {
        this.totalTakeMoney = totalTakeMoney;
    }
}
