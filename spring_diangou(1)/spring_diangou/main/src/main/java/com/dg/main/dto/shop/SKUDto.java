package com.dg.main.dto.shop;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
public class SKUDto {
    private Long specificationId;
    private String labelName;
    private List<String> params= Lists.newArrayList();
}
