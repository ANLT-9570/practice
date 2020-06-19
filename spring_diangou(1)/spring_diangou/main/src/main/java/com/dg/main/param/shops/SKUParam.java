package com.dg.main.param.shops;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
@NoArgsConstructor
public class SKUParam {



    private Long id;////id

    private String dimensions;//尺寸@Column(name = "Sspecifications_id")

    private Integer stock;//库存

    private Long price;//价格

    private Long DiscountPrice;//折后价

    private String weight;//体重

    private String colour;//颜色

    private String img;//图片

    private Long SpecificationsId;//规格表的Id

    private String thumbnailImage;



}
