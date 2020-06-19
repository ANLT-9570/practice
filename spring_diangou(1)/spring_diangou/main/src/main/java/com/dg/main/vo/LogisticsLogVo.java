package com.dg.main.vo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class LogisticsLogVo implements Serializable {

    private Long id;
    private String AcceptStation;
    private Timestamp AcceptTime;
    private Long logisticsStateId;
    private String orderCode;

    private String logisticCode;
    private String logisticsType;

    private Long specificationId;
    private String specificationImg;
}
