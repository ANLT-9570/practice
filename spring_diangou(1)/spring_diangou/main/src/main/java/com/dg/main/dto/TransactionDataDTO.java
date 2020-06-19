package com.dg.main.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class TransactionDataDTO {
    private String dayDirectSum;//直营交易额
    private String daySubSum;//下级交易额
    private String dayCount;//合计交易额
    private String monthDirectSum;//直营交易额
    private String monthSubSum;//下级交易额
    private String monthCount;//合计交易额
}
