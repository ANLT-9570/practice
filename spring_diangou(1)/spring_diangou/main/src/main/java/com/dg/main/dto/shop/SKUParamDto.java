package com.dg.main.dto.shop;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class SKUParamDto {
    private Long id;
    private Integer stock;
    private Long price;
    private String img;
}
