package com.dg.main.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class MyPartnerDTO {
    String name;
    String Image;
    String phone;
    String MonthTransaction;//本月交易额
    String referrer;//推荐人
    String UserId;
    String shiId;
}
