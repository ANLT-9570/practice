package com.dg.main.trans_mapper_obj;



import com.dg.main.Entity.orders.LogisticsLog;

import java.util.List;

public class LogisticsObj {
    private String LogisticCode;
    private String ShipperCode;
    private List<LogisticsLog> Traces;
    private String State;
    private String EBusinessID;
    private String Success;

    @Override
    public String toString() {
        return "LogisticsObj{" +
                "LogisticCode='" + LogisticCode + '\'' +
                ", ShipperCode='" + ShipperCode + '\'' +
                ", Traces=" + Traces +
                ", State='" + State + '\'' +
                ", EBusinessID='" + EBusinessID + '\'' +
                ", Success='" + Success + '\'' +
                '}';
    }

    public LogisticsObj() {
    }

    public String getLogisticCode() {
        return LogisticCode;
    }

    public void setLogisticCode(String logisticCode) {
        LogisticCode = logisticCode;
    }

    public String getShipperCode() {
        return ShipperCode;
    }

    public void setShipperCode(String shipperCode) {
        ShipperCode = shipperCode;
    }

    public List<LogisticsLog> getTraces() {
        return Traces;
    }

    public void setTraces(List<LogisticsLog> traces) {
        Traces = traces;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getEBusinessID() {
        return EBusinessID;
    }

    public void setEBusinessID(String EBusinessID) {
        this.EBusinessID = EBusinessID;
    }

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String success) {
        Success = success;
    }
}
