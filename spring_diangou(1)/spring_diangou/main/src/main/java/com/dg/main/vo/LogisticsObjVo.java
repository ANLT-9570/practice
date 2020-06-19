package com.dg.main.vo;

import com.dg.main.Entity.orders.LogisticsLog;
import lombok.Data;

import java.util.List;

@Data
public class LogisticsObjVo {
    private String LogisticCode;
    private String ShipperCode;
    private LogisticsLog Trace;
    private String State;
    private String EBusinessID;
    private String Success;

    @Override
    public String toString() {
        return "LogisticsObjVo{" +
                "LogisticCode='" + LogisticCode + '\'' +
                ", ShipperCode='" + ShipperCode + '\'' +
                ", Trace=" + Trace +
                ", State='" + State + '\'' +
                ", EBusinessID='" + EBusinessID + '\'' +
                ", Success='" + Success + '\'' +
                '}';
    }
}
