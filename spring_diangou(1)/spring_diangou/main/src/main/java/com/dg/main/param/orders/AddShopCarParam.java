package com.dg.main.param.orders;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class AddShopCarParam {
    private Long userId;
    private Long shopId;
    private Long specificationId;
    private Long number;
    private Long price;
    private Long sizeId;


}
