package com.dg.main.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class RefundedDTO {
    private String dayCount;//合计
    private String dayDirectRefunded;//直营退货
    private String daySubRefunded;//下级退货
    private String monthCount;//合计
    private String monthDirectRefunded;//直营退货
    private String monthSubRefunded;//下级退货
}
