package com.dg.main.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class MDTransactionDTO {
    private String dayRate;//费率
    private String daySaleNumber;//销量
    private String daySum;
    private String monthRate;
    private String monthSaleNumber;
    private String monthSum;
}
