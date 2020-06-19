package com.dg.main.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class ShopsDataDTO {
    private String userName;
    private String createTime;
    private String shopNum;
    private String dayDirectNewAdd;
    private String daySubNewAdd;
    private String dayCount;
    private String monthDirectNewAdd;
    private String monthSubNewAdd;
    private String monthCount;
}
